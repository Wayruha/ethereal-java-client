package trade.wayruha.ethereal.dto.request.signature;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import trade.wayruha.ethereal.dto.request.PlaceOrderParams;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({"sender", "subaccount", "quantity", "price", "reduceOnly", "side", "engineType", "productId", "nonce", "signedAt"})
@AllArgsConstructor
public class PlaceOrderSignature {
  private static final BigDecimal multiplicand = new BigDecimal("1000000000"); //1_000_000_000

  String subaccount; // Bytes32 encoded subaccount name (0x prefix, zero padded)
  String sender; // Address of account
  long nonce; // Message nonce timestamp (nanoseconds since Unix Epoch)
  String quantity;
  String price;
  byte side;
  long productId; // Onchain generated productId from prior product registration
  byte engineType;
  long signedAt; // timestamp
  Boolean reduceOnly = false;

  public static PlaceOrderSignature fromPlaceOrderParams(PlaceOrderParams placeOrderParams) {
    return new PlaceOrderSignature(
        placeOrderParams.getSubaccount(),
        placeOrderParams.getSender(),
        Long.parseLong(placeOrderParams.getNonce()),
        placeOrderParams.getQuantity()
            .multiply(multiplicand)
            .toBigIntegerExact()
            .toString(),
        (placeOrderParams.getPrice() != null ? placeOrderParams.getPrice() : java.math.BigDecimal.ZERO)
            .multiply(multiplicand)
            .toBigIntegerExact()
            .toString(),
        (byte) placeOrderParams.getSide().getSide(),
        placeOrderParams.getOnchainId(),
        (byte) placeOrderParams.getEngineType().getType(),
        placeOrderParams.getSignedAt(),
        placeOrderParams.getReduceOnly() != null ? placeOrderParams.getReduceOnly() : false
    );
  }
}
