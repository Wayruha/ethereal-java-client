package trade.wayruha.ethereal.service;

import lombok.SneakyThrows;
import org.web3j.crypto.StructuredData;
import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.EthereumSignature;
import trade.wayruha.ethereal.dto.request.CancelOrderParams;
import trade.wayruha.ethereal.dto.request.CancelOrderRequest;
import trade.wayruha.ethereal.dto.request.PlaceOrderParams;
import trade.wayruha.ethereal.dto.request.PlaceOrderRequest;
import trade.wayruha.ethereal.dto.request.signature.CancelOrderSignature;
import trade.wayruha.ethereal.dto.request.signature.PlaceOrderSignature;
import trade.wayruha.ethereal.dto.request.signature.SignatureType;
import trade.wayruha.ethereal.dto.response.*;
import trade.wayruha.ethereal.service.endpoint.TradeEndpoints;
import trade.wayruha.ethereal.util.TransactionSignatureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TradeService extends ServiceBase {
  private static final SignatureType PLACE_ORDER_SIGNATURE_TYPE = SignatureType.TRADE_ORDER;
  private static final SignatureType CANCEL_ORDER_SIGNATURE_TYPE = SignatureType.CANCEL_ORDER;

  private final TradeEndpoints tradeApi;
  private final RpcConfigResponse.Domain exchangeDomain;
  private final List<RpcConfigResponse.SignatureField> placeOrderSignatureFields;
  private final List<RpcConfigResponse.SignatureField> cancelOrderSignatureFields;

  public TradeService(EtherealConfig config, RpcConfigResponse rpcConfig) {
    super(config);
    this.tradeApi = createService(TradeEndpoints.class);
    this.exchangeDomain = rpcConfig.getDomain();
    this.placeOrderSignatureFields = rpcConfig.getParsedSignatureTypes().get(PLACE_ORDER_SIGNATURE_TYPE.getType());
    this.cancelOrderSignatureFields = rpcConfig.getParsedSignatureTypes().get(CANCEL_ORDER_SIGNATURE_TYPE.getType());
  }

  public PageableResponse<OrderInfo> getOrdersPageable(String subaccountId, boolean includeClosed, String cursor) {
    return client.executeSync(tradeApi.getOrders(subaccountId, !includeClosed ? true : null, cursor)); // invert logic for includeClosed: true -> null, false -> true
  }

  public List<OrderInfo> getAllOrders(String subaccountId, boolean includeClosed) {
    String cursor = null;
    boolean nextCursor = false;

    final List<OrderInfo> result = new ArrayList<>();
    do {
      final PageableResponse<OrderInfo> ordersResponse = getOrdersPageable(subaccountId, includeClosed, cursor);
      result.addAll(ordersResponse.getItems());
      cursor = ordersResponse.getNextCursor();
      nextCursor = ordersResponse.isHasNext();
    } while (nextCursor);
    return result;
  }

  public OrderInfo getOrderById(String orderId) {
    return client.executeSync(tradeApi.getOrderById(orderId));
  }

  public PageableResponse<OrderFill> getOrderFills(String subaccountId) {
    return client.executeSync(tradeApi.getOrderFills(subaccountId));
  }

  @SneakyThrows
  public PlaceOrderResponse placeOrder(PlaceOrderParams placeOrderParams) {
    final PlaceOrderRequest placeOrderRequest = preparePlaceOrderRequest(placeOrderParams);
    return client.executeSync(tradeApi.placeOrder(placeOrderRequest));
  }

  @SneakyThrows
  public CancelOrdersResponse cancelOrder(CancelOrderParams cancelOrderParams) {
    final CancelOrderRequest cancelOrderRequest = prepareCancelOrderRequest(cancelOrderParams);
    return client.executeSync(tradeApi.cancelOrders(cancelOrderRequest));
  }

  public PageableResponse<PositionInfo> getPositionsPageable(String subaccountId, Boolean open, String cursor) {
    return client.executeSync(tradeApi.getPositions(subaccountId, open, cursor));
  }

  public List<PositionInfo> getAllPositions(String subaccountId, Boolean open) {
    String cursor = null;
    boolean nextCursor = false;

    final List<PositionInfo> result = new ArrayList<>();
    do {
      final PageableResponse<PositionInfo> positionsResponse = getPositionsPageable(subaccountId, open, cursor);
      result.addAll(positionsResponse.getItems());
      cursor = positionsResponse.getNextCursor();
      nextCursor = positionsResponse.isHasNext();
    } while (nextCursor);
    return result;
  }

  public PositionInfo getPositionById(String positionId) {
    return client.executeSync(tradeApi.getPositionById(positionId));
  }

  private PlaceOrderRequest preparePlaceOrderRequest(PlaceOrderParams placeOrderParams) {
    final LinkedHashMap<String, Object> payloadFieldsMap = getObjectMapper().convertValue(PlaceOrderSignature.fromPlaceOrderParams(placeOrderParams), LinkedHashMap.class);

    HashMap<String, List<StructuredData.Entry>> types = new LinkedHashMap<>();
    types.put(PLACE_ORDER_SIGNATURE_TYPE.getType(), placeOrderSignatureFields.stream().map(signatureField -> new StructuredData.Entry(signatureField.getName(), signatureField.getType())).collect(Collectors.toList()));

    final EthereumSignature ethereumSignature = TransactionSignatureUtil.signEip712(
        this.client.getConfig().getPrivateKey(),
        exchangeDomain,
        PLACE_ORDER_SIGNATURE_TYPE,
        types,
        payloadFieldsMap);
    return new PlaceOrderRequest(placeOrderParams, ethereumSignature.toHexSignature());
  }

  private CancelOrderRequest prepareCancelOrderRequest(CancelOrderParams cancelOrderParams) {
    final LinkedHashMap<String, Object> payloadFieldsMap = getObjectMapper().convertValue(CancelOrderSignature.fromCancelOrderParams(cancelOrderParams), LinkedHashMap.class);

    HashMap<String, List<StructuredData.Entry>> types = new LinkedHashMap<>();
    types.put(CANCEL_ORDER_SIGNATURE_TYPE.getType(), cancelOrderSignatureFields.stream().map(signatureField -> new StructuredData.Entry(signatureField.getName(), signatureField.getType())).collect(Collectors.toList()));

    final EthereumSignature ethereumSignature = TransactionSignatureUtil.signEip712(
        this.client.getConfig().getPrivateKey(),
        exchangeDomain,
        CANCEL_ORDER_SIGNATURE_TYPE,
        types,
        payloadFieldsMap);
    return new CancelOrderRequest(cancelOrderParams, ethereumSignature.toHexSignature());
  }
}
