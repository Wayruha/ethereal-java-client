package trade.wayruha.ethereal.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CancelOrderParams {
  String subaccount;
  String sender;
  String nonce; // Message nonce timestamp (nanoseconds since Unix Epoch)
  // orderIds + clientOrderIds < 200
  List<String> orderIds;
  List<String> clientOrderIds;
}
