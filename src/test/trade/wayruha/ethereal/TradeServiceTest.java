package trade.wayruha.ethereal;

import lombok.SneakyThrows;
import trade.wayruha.ethereal.dto.EngineType;
import trade.wayruha.ethereal.dto.OrderSide;
import trade.wayruha.ethereal.dto.TimeInForce;
import trade.wayruha.ethereal.dto.request.CancelOrderParams;
import trade.wayruha.ethereal.dto.request.PlaceOrderParams;
import trade.wayruha.ethereal.dto.response.*;
import trade.wayruha.ethereal.service.AccountService;
import trade.wayruha.ethereal.service.RpcService;
import trade.wayruha.ethereal.service.TradeService;

import java.math.BigDecimal;
import java.util.List;

public class TradeServiceTest {
  private static final Long CLIENT_ORDER_ID = Math.abs(new java.util.Random().nextLong());
  private static String subaccount;
  private static String subaccountUUID;
  private static String sender;

  private static TradeService tradeService;
  private static AccountService accountService;

  @SneakyThrows
  public static void main(String[] args) {
    final EtherealConfig etherealConfig = new EtherealConfig(false);
    final RpcConfigResponse rpcConfig = new RpcService(etherealConfig).getRpcConfig();
    accountService = new AccountService(etherealConfig);
    tradeService = new TradeService(etherealConfig, rpcConfig);

    final AllSubaccountsResponse subaccounts = accountService.getSubaccounts(null);

    sender = etherealConfig.getPublicKey();
    subaccountUUID = subaccounts.getItems().get(0).getId();
    subaccount = subaccounts.getItems().get(0).getName();

    final PlaceOrderResponse limitOrderResponse = placeLimitOrder();
    final PlaceOrderResponse marketOrderResponse = placeMarketOrder();
    getOrders();
    getOrderById(limitOrderResponse.getId());
    cancelOrder();
    getOrderFills();
    Thread.sleep(5000);
    final PositionsInfoResponse positions = getPositions();
    if (!positions.getItems().isEmpty())
      getPositionById(positions.getItems().get(0).getId());
  }

  private static PlaceOrderResponse placeLimitOrder() {
    final long epochMillis = System.currentTimeMillis();
    final long epochNanos = epochMillis * 1_000_000L;

    final String nonce = Long.toString(epochNanos);
    final long signedAt = epochMillis / 1000;

    final PlaceOrderParams placeOrderParams = PlaceOrderParams.LimitOrder(
        subaccount,
        sender,
        nonce,
        new BigDecimal("0.01"),
        OrderSide.BUY,
        1L,
        EngineType.PERP,
        signedAt,
        new BigDecimal(88000),
        TimeInForce.GTD,
        true
        );
    placeOrderParams.setClientOrderId(String.valueOf(CLIENT_ORDER_ID));

    final PlaceOrderResponse placeOrderResponse = tradeService.placeOrder(placeOrderParams);
    System.out.println(placeOrderResponse);
    return placeOrderResponse;
  }

  private static PlaceOrderResponse placeMarketOrder() {
    final long epochMillis = System.currentTimeMillis();
    final long epochNanos = epochMillis * 1_000_000L;

    final String nonce = Long.toString(epochNanos);
    final long signedAt = epochMillis / 1000;

    final PlaceOrderParams placeOrderParams = PlaceOrderParams.MarketOrder(
        subaccount,
        sender,
        nonce,
        new BigDecimal("0.01"),
        OrderSide.BUY,
        1L,
        EngineType.PERP,
        signedAt
    );
    placeOrderParams.setClientOrderId(String.valueOf(CLIENT_ORDER_ID));

    final PlaceOrderResponse placeOrderResponse = tradeService.placeOrder(placeOrderParams);
    System.out.println(placeOrderResponse);
    return placeOrderResponse;
  }

  private static void getOrders() {
    final OrdersInfoResponse orders = tradeService.getOrders(subaccountUUID, false);
    System.out.println(orders);
  }

  private static void getOrderById(String orderId) {
    final OrderInfo order = tradeService.getOrderById(orderId);
    System.out.println(order);
  }

  private static void getOrderFills() {
    final OrderFillsResponse orderFills = tradeService.getOrderFills(subaccountUUID);
    System.out.println(orderFills);
  }

  private static void cancelOrder() {
    final long epochMillis = System.currentTimeMillis();
    final long epochNanos = epochMillis * 1_000_000L;
    final String nonce = Long.toString(epochNanos);

    final CancelOrderParams cancelOrderParams = new CancelOrderParams(
        subaccount,
        sender,
        nonce,
        null,
        List.of(String.valueOf(CLIENT_ORDER_ID))
    );
    final CancelOrdersResponse cancelOrdersResponse = tradeService.cancelOrder(cancelOrderParams);
    System.out.println(cancelOrdersResponse);
    assert cancelOrdersResponse != null;
  }

  private static PositionsInfoResponse getPositions() {
    final PositionsInfoResponse positions = tradeService.getPositions(subaccountUUID, true);
    System.out.println(positions);
    return positions;
  }

  private static void getPositionById(String id) {
    final PositionInfo position = tradeService.getPositionById(id);
    System.out.println(position);
  }
}
