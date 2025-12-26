package trade.wayruha.ethereal.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.response.OrderFillsResponse;
import trade.wayruha.ethereal.dto.response.OrdersInfoResponse;
import trade.wayruha.ethereal.dto.response.ProductMarketPriceInfo;
import trade.wayruha.ethereal.dto.response.TokenTransferResponse;
import trade.wayruha.ethereal.dto.wsrequest.*;
import trade.wayruha.ethereal.dto.wsresponse.WSBookDepthUpdate;
import trade.wayruha.ethereal.dto.wsresponse.WSSubaccountLiquidationUpdate;
import trade.wayruha.ethereal.dto.wsresponse.WSTradeFillUpdate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static trade.wayruha.ethereal.dto.wsrequest.WSBookDepthSubscription.BOOK_DEPTH_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSMarketPriceSubscription.MARKET_PRICE_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSOrderFillSubscription.ORDER_FILL_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSOrderUpdateSubscription.ORDER_UPDATE_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSSubaccountLiquidationSubscription.SUBACCOUNT_LIQUIDATION_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSTokenTransferSubscription.TOKEN_TRANSFER_EVENT_TYPE;
import static trade.wayruha.ethereal.dto.wsrequest.WSTradeFillSubscription.TRADE_FILL_EVENT_TYPE;

public class WebSocketClientFactory {

  private final EtherealConfig config;
  @Setter
  private ObjectMapper objectMapper;

  public WebSocketClientFactory(EtherealConfig config) {
    this.config = config;
    this.objectMapper = config.getObjectMapper();
  }

  //public subscriptions
  public WebSocketClient<WSBookDepthUpdate> orderBookSubscription(Collection<String> productIds, WebSocketCallback<WSBookDepthUpdate> callback) {
    final List<WSBookDepthSubscription> channels = productIds.stream()
        .map(WSBookDepthSubscription::new)
        .collect(Collectors.toCollection(ArrayList::new));
    final WebSocketClient<WSBookDepthUpdate> client = new WebSocketClient<>(config, objectMapper, callback, BOOK_DEPTH_EVENT_TYPE);
    client.connect(channels);
    return client;
  }

  public WebSocketClient<ProductMarketPriceInfo> marketPriceSubscription(Collection<String> productIds, WebSocketCallback<ProductMarketPriceInfo> callback) {
    final List<WSMarketPriceSubscription> channels = productIds.stream()
        .map(WSMarketPriceSubscription::new)
        .collect(Collectors.toCollection(ArrayList::new));
    final WebSocketClient<ProductMarketPriceInfo> client = new WebSocketClient<>(config, objectMapper, callback, MARKET_PRICE_EVENT_TYPE);
    client.connect(channels);
    return client;
  }

  public WebSocketClient<OrderFillsResponse> orderFillSubscription(String subaccountId, WebSocketCallback<OrderFillsResponse> callback) {
    final WSOrderFillSubscription channel = new WSOrderFillSubscription(subaccountId);
    final WebSocketClient<OrderFillsResponse> client = new WebSocketClient<>(config, objectMapper, callback, ORDER_FILL_EVENT_TYPE);
    client.connect(List.of(channel));
    return client;
  }

  public WebSocketClient<WSTradeFillUpdate> tradeFillSubscription(Collection<String> productIds, WebSocketCallback<WSTradeFillUpdate> callback) {
    final List<WSTradeFillSubscription> channels = productIds.stream()
        .map(WSTradeFillSubscription::new)
        .collect(Collectors.toCollection(ArrayList::new));
    final WebSocketClient<WSTradeFillUpdate> client = new WebSocketClient<>(config, objectMapper, callback, TRADE_FILL_EVENT_TYPE);
    client.connect(channels);
    return client;
  }

  public WebSocketClient<OrdersInfoResponse> orderUpdateSubscription(String subaccountId, WebSocketCallback<OrdersInfoResponse> callback) {
    final WSOrderUpdateSubscription channel = new WSOrderUpdateSubscription(subaccountId);
    final WebSocketClient<OrdersInfoResponse> client = new WebSocketClient<>(config, objectMapper, callback, ORDER_UPDATE_EVENT_TYPE);
    client.connect(List.of(channel));
    return client;
  }

  public WebSocketClient<WSSubaccountLiquidationUpdate> subaccountLiquidationSubscription(String subaccountId, WebSocketCallback<WSSubaccountLiquidationUpdate> callback) {
    final WSSubaccountLiquidationSubscription channel = new WSSubaccountLiquidationSubscription(subaccountId);
    final WebSocketClient<WSSubaccountLiquidationUpdate> client = new WebSocketClient<>(config, objectMapper, callback, SUBACCOUNT_LIQUIDATION_EVENT_TYPE);
    client.connect(List.of(channel));
    return client;
  }

  public WebSocketClient<TokenTransferResponse> tokenTransferSubscription(String subaccountId, WebSocketCallback<TokenTransferResponse> callback) {
    final WSTokenTransferSubscription channel = new WSTokenTransferSubscription(subaccountId);
    final WebSocketClient<TokenTransferResponse> client = new WebSocketClient<>(config, objectMapper, callback, TOKEN_TRANSFER_EVENT_TYPE);
    client.connect(List.of(channel));
    return client;
  }
}
