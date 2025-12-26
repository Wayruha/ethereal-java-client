package trade.wayruha.ethereal.dto.request;

import lombok.Value;

import java.util.List;

@Value
public class CancelOrderRequest {
  String subaccount;
  String sender;
  String nonce; // Message nonce timestamp (nanoseconds since Unix Epoch)
  // orderIds + clientOrderIds < 200
  List<String> orderIds;
  List<String> clientOrderIds;
}
