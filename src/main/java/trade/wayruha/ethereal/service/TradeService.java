package trade.wayruha.ethereal.service;

import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.request.CancelOrderRequest;
import trade.wayruha.ethereal.dto.request.PlaceOrderRequest;
import trade.wayruha.ethereal.dto.response.*;
import trade.wayruha.ethereal.service.endpoint.TradeEndpoints;

public class TradeService extends ServiceBase {
  private final TradeEndpoints tradeApi;

  public TradeService(EtherealConfig config) {
    super(config);
    this.tradeApi = createService(TradeEndpoints.class);
  }

  public OrdersInfoResponse getOrders(String subaccountId) {
    return client.executeSync(tradeApi.getOrders(subaccountId));
  }

  public OrderInfo getOrderById(String orderId) {
    return client.executeSync(tradeApi.getOrderById(orderId));
  }

  public OrderFillsResponse getOrderFills(String subaccountId) {
    return client.executeSync(tradeApi.getOrderFills(subaccountId));
  }

  public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
    return client.executeSync(tradeApi.placeOrder(placeOrderRequest));
  }

  public CancelOrdersResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
    return client.executeSync(tradeApi.cancelOrders(cancelOrderRequest));
  }

  public PositionsInfoResponse getPositions(String subaccountId) {
    return client.executeSync(tradeApi.getPositions(subaccountId));
  }

  public PositionInfo getPositionById(String positionId) {
    return client.executeSync(tradeApi.getPositionById(positionId));
  }
}
