package trade.wayruha.ethereal.dto.request.signature;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import trade.wayruha.ethereal.dto.request.CancelOrderParams;

@Data
@JsonPropertyOrder({"sender", "subaccount", "nonce"})
@AllArgsConstructor
public class CancelOrderSignature {
  String subaccount; // Bytes32 encoded subaccount name (0x prefix, zero padded)
  String sender; // Address of account
  long nonce; // Message nonce timestamp (nanoseconds since Unix Epoch)

  public static CancelOrderSignature fromCancelOrderParams(CancelOrderParams cancelOrderParams) {
    return new CancelOrderSignature(
        cancelOrderParams.getSubaccount(),
        cancelOrderParams.getSender(),
        Long.parseLong(cancelOrderParams.getNonce())
    );
  }
}
