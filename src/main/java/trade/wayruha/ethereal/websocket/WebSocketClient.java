package trade.wayruha.ethereal.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.wsrequest.WSSubscription;
import trade.wayruha.ethereal.util.IdGenerator;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class WebSocketClient<T> {
  protected static long WEB_SOCKET_RECONNECTION_DELAY_MS = 10_000;
  protected final EtherealConfig config;
  protected final ObjectMapper objectMapper;
  protected final WebSocketCallback<T> callback;
  @Getter
  protected final int id;
  protected final String logPrefix;
  protected final AtomicInteger reconnectionCounter;
  protected List<WSSubscription> subscriptions;
  private Socket socket;
  private final String event;
  private static final CountDownLatch connectionLatch = new CountDownLatch(1);
  @Getter
  protected long lastReceivedTime;

  @SneakyThrows
  public WebSocketClient(EtherealConfig config, ObjectMapper mapper, WebSocketCallback<T> callback, String event) {
    this.config = config;
    this.callback = callback;
    this.objectMapper = mapper;
    this.event = event;
    this.subscriptions = new ArrayList<>();
    this.reconnectionCounter = new AtomicInteger(0);
    this.id = IdGenerator.getNextId();
    this.logPrefix = "[ws-" + this.id + "]";
  }

  @SneakyThrows
  public void connect(Collection<? extends WSSubscription> subscriptions) {
    log.info("{} Connecting to Ethereal WebSocket...", logPrefix);

    final URI serverUri = URI.create(config.getWebSocketHost());

    final IO.Options options = new IO.Options();
    options.transports = new String[]{"websocket"};
    options.upgrade = false;
    options.timeout = 10000;

    socket = IO.socket(serverUri, options);

    socket.on(Socket.EVENT_CONNECT, args -> {
      log.info("{} Connected!", logPrefix);
      connectionLatch.countDown();
    });

    socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
      log.error("{} Connection failed: {}", logPrefix, Arrays.toString(args));
      connectionLatch.countDown();
    });

    socket.on(event, args -> handleMessage(args[0].toString()));

    socket.on("exception", args -> log.error("{} Server exception: {}", logPrefix, Arrays.toString(args)));

    socket.connect();

    if (!connectionLatch.await(15, TimeUnit.SECONDS)) {
      throw new RuntimeException("Connection timeout");
    }

    subscribe(subscriptions);
  }

  public void subscribe(Collection<? extends WSSubscription> subscriptions) {
    subscriptions.forEach(this::subscribe);
  }

  @SneakyThrows
  public void subscribe(WSSubscription subscription) {
    final Map<String, Object> payload = objectMapper.convertValue(subscription, new TypeReference<>() {
    });
    socket.emit("subscribe", payload);
    this.subscriptions.add(subscription);
  }

  public void close() {
    log.info("{} Closing WS.", logPrefix);
    if (socket != null) {
      socket.disconnect();
      socket = null;
    }
    this.subscriptions.clear();
  }

  @SneakyThrows
  public boolean reConnect() {
    boolean success = false;
    while (!success && (config.isWebSocketReconnectAlways() || reconnectionCounter.incrementAndGet() < config.getWebSocketMaxReconnectAttempts())) {
      try {
        log.debug("{} Try to reconnect. Attempt #{}", logPrefix, reconnectionCounter.get());
        close();
        connect(this.subscriptions);
        success = true;
      } catch (Exception e) {
        log.error("{} [Connection error] Error while reconnecting: {}", logPrefix, e.getMessage(), e);
        Thread.sleep(WEB_SOCKET_RECONNECTION_DELAY_MS);
      }
      log.info("{} Successfully reconnected to SocketIO channels: {}.", logPrefix, this.subscriptions);
    }
    return success;
  }

  private void handleMessage(String message) {
    lastReceivedTime = System.currentTimeMillis();
    log.trace("{} onMessage WS event: {}", logPrefix, message);
    try {
      final T data = parseResponseBody(message);
      if (data != null) callback.onResponse(data);
    } catch (Exception e) {
      log.error("{} WS message parsing failed. Closing it. Response: {}", log, message, e);
      close();
    }
  }

  private void handleFailure(Throwable ex) {
    if (!reConnect()) {
      log.warn("{} [Connection error] Connection will be closed due to error: {}", logPrefix, ex.getMessage());
      close();
      callback.onFailure(ex, null);
    }
  }

  private T parseResponseBody(String message) throws IOException {
    final ObjectNode response = objectMapper.readValue(message, ObjectNode.class);
    final JsonNode errorNode = response.get("error");
    if (errorNode != null) {
      throw new RuntimeException();
    }
    return objectMapper.convertValue(response, callback.getType());
  }

}