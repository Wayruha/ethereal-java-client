package trade.wayruha.ethereal.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import trade.wayruha.ethereal.dto.*;

import java.io.IOException;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceOrderParams {
  // REQUIRED BY DEFAULT
  /**
   * Bytes32 encoded subaccount name (0x prefix, zero padded)
   */
  final String subaccount;

  /**
   * Address of account
   */
  final String sender;

  /**
   * Message nonce timestamp (nanoseconds since Unix Epoch)
   */
  final String nonce;
  final OrderType type;

  @JsonSerialize(using = BigDecimalAsStringSerializer.class)
  final BigDecimal quantity;
  final OrderSide side;

  /**
   * Onchain generated productId from prior product registration
   */
  final Long onchainId;
  final EngineType engineType;

  /**
   * timestamp
   */
  final Long signedAt;

  // REQUIRED FOR LIMIT ORDER
  BigDecimal price;
  TimeInForce timeInForce;
  Boolean postOnly;

  // NOT REQUIRED
  String clientOrderId; // A subaccount scoped unique client-generated order id (either a UUID or alphanumeric string up to 32 characters)
  Boolean reduceOnly;
  Boolean close; // Order closes the entire current position, requires zero quantity and reduceOnly
  BigDecimal stopPrice; // requires stopType
  StopType stopType; // requires non-zero stopPrice

  /**
   * default 3 month
   */
  Long expiresAt;

  /**
   * Group Id (UUID) for linking orders together in OCO/OTO relationships
   */
  String groupId;
  GroupContingencyType groupContingencyType;

  public static PlaceOrderParams LimitOrder(String subaccount, String sender, String nonce, BigDecimal quantity, OrderSide side, Long onchainId, EngineType engineType, Long signedAt, BigDecimal price, TimeInForce timeInForce, Boolean postOnly) {
    final PlaceOrderParams placeOrderParams = new PlaceOrderParams(subaccount, sender, nonce, OrderType.LIMIT, quantity, side, onchainId, engineType, signedAt);
    placeOrderParams.setPrice(price);
    placeOrderParams.setTimeInForce(timeInForce);
    placeOrderParams.setPostOnly(postOnly);
    return placeOrderParams;

  }

  public static PlaceOrderParams MarketOrder(String subaccount, String sender, String nonce, BigDecimal quantity, OrderSide side, Long onchainId, EngineType engineType, Long signedAt) {
    return new PlaceOrderParams(subaccount, sender, nonce, OrderType.MARKET, quantity, side, onchainId, engineType, signedAt);
  }

  public static class BigDecimalAsStringSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
      jsonGenerator.writeString(value.toPlainString());
    }
  }
}
