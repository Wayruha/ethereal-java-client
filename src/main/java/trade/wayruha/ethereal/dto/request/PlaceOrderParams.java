package trade.wayruha.ethereal.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import trade.wayruha.ethereal.dto.*;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceOrderParams {
  // required by default
  String subaccount; // Bytes32 encoded subaccount name (0x prefix, zero padded)
  String sender; // Address of account
  String nonce; // Message nonce timestamp (nanoseconds since Unix Epoch)
  OrderType type;
  BigDecimal quantity;
  OrderSide side;
  Long onchainId; // Onchain generated productId from prior product registration
  EngineType engineType;
  Long signedAt; // timestamp

  // required for limit order
  BigDecimal price;
  TimeInForce timeInForce;
  Boolean postOnly;

  // not required
  String clientOrderId; // A subaccount scoped unique client-generated order id (either a UUID or alphanumeric string up to 32 characters)
  Boolean reduceOnly;
  Boolean close; // Order closes the entire current position, requires zero quantity and reduceOnly
  BigDecimal stopPrice; // requires stopType
  StopType stopType; // requires non-zero stopPrice
  Long expiresAt;
  String groupId; // Group Id (UUID) for linking orders together in OCO/OTO relationships
  GroupContingencyType groupContingencyType;

  private PlaceOrderParams(String subaccount, String sender, String nonce, OrderType type, BigDecimal quantity, OrderSide side, Long onchainId, EngineType engineType, Long signedAt, BigDecimal price, TimeInForce timeInForce, Boolean postOnly, String clientOrderId, Boolean reduceOnly, Boolean close, BigDecimal stopPrice, StopType stopType, Long expiresAt, String groupId, GroupContingencyType groupContingencyType) {
    this.subaccount = subaccount;
    this.sender = sender;
    this.nonce = nonce;
    this.type = type;
    this.quantity = quantity;
    this.side = side;
    this.onchainId = onchainId;
    this.engineType = engineType;
    this.signedAt = signedAt;
    this.price = price;
    this.timeInForce = timeInForce;
    this.postOnly = postOnly;
    this.clientOrderId = clientOrderId;
    this.reduceOnly = reduceOnly;
    this.close = close;
    this.stopPrice = stopPrice;
    this.stopType = stopType;
    this.expiresAt = expiresAt;
    this.groupId = groupId;
    this.groupContingencyType = groupContingencyType;
  }

  public static PlaceOrderParams LimitOrder(String subaccount, String sender, String nonce, BigDecimal quantity, OrderSide side, Long onchainId, EngineType engineType, Long signedAt, BigDecimal price, TimeInForce timeInForce, Boolean postOnly, String clientOrderId, Boolean reduceOnly, Boolean close, BigDecimal stopPrice, StopType stopType, Long expiresAt, String groupId, GroupContingencyType groupContingencyType) {
    return new PlaceOrderParams(subaccount, sender, nonce, OrderType.LIMIT, quantity, side, onchainId, engineType, signedAt, price, timeInForce, postOnly, clientOrderId, reduceOnly, close, stopPrice, stopType, expiresAt, groupId, groupContingencyType);
  }

  public static PlaceOrderParams MarketOrder(String subaccount, String sender, String nonce, BigDecimal quantity, OrderSide side, Long onchainId, EngineType engineType, Long signedAt, String clientOrderId, Boolean reduceOnly, Boolean close, BigDecimal stopPrice, StopType stopType, Long expiresAt, String groupId, GroupContingencyType groupContingencyType) {
    return new PlaceOrderParams(subaccount, sender, nonce, OrderType.MARKET, quantity, side, onchainId, engineType, signedAt, null, null, null, clientOrderId, reduceOnly, close, stopPrice, stopType, expiresAt, groupId, groupContingencyType);
  }
}
