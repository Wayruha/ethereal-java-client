package trade.wayruha.ethereal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import static trade.wayruha.ethereal.config.Constant.HTTP_CLIENT_TIMEOUT_MS;


@Data
@NoArgsConstructor
public class EtherealConfig {
  //mainnet
  public static final String MAINNET_HOST = "https://api.ethereal.trade/";
  public static final String MAINNET_WS_HOST = "wss://ws.ethereal.trade/v1/stream";

  //testnet
  public static final String TESTNET_HOST = "https://api.etherealtest.net/";
  public static final String TESTNET_WS_HOST = "wss://ws.etherealtest.net/v1/stream";

  private String host;
  private String webSocketHost;
  private String privateKey;
  private String publicKey;
  private boolean isMainnet;

  public EtherealConfig(String publicKey, String privateKey) {
    this(publicKey, privateKey, true);
  }

  public EtherealConfig(String publicKey, String privateKey, boolean isMainnet) {
    this(isMainnet);
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  public EtherealConfig(boolean isMainnet) {
    this.isMainnet = isMainnet;
    this.host = isMainnet ? MAINNET_HOST : TESTNET_HOST;
    this.webSocketHost = isMainnet ? MAINNET_WS_HOST : TESTNET_WS_HOST;
  }

  /**
   * Host connection timeout.
   */
  private long httpConnectTimeout = HTTP_CLIENT_TIMEOUT_MS;
  /**
   * The host reads the information timeout.
   */
  private long httpReadTimeout = HTTP_CLIENT_TIMEOUT_MS;
  /**
   * The host writes the information timeout.
   */
  private long httpWriteTimeout = HTTP_CLIENT_TIMEOUT_MS;
  /**
   * Retry on failed connection, default true.
   */
  private boolean retryOnConnectionFailure = true;
  /**
   * Should we log request's data?
   */
  private boolean httpLogRequestData = false;

  /**
   * WebSocket should try re-connecting on fail forever
   */
  private boolean webSocketReconnectAlways = false;
  /**
   * If not forever, then how many times?
   */
  private int webSocketMaxReconnectAttempts = 3;
  private int webSocketPingIntervalSec = 45;

  private ObjectMapper objectMapper = createObjectMapper();

  private static ObjectMapper createObjectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    mapper
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    return mapper;
  }
}
