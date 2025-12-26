package trade.wayruha.ethereal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PlaceOrderRequest {
  @JsonProperty("data")
  PlaceOrderParams placeOrderParams;
  @JsonProperty("signature")
  String signature;
}
