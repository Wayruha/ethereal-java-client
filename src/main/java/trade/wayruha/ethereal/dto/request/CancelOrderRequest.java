package trade.wayruha.ethereal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CancelOrderRequest {
  @JsonProperty("data")
  CancelOrderParams cancelOrderParams;
  @JsonProperty("signature")
  String signature;
}
