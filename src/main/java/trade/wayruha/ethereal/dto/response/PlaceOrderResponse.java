package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceOrderResponse {
  private String id;
  private String clientOrderId;
  private BigDecimal filled;
  @JsonProperty("result")
  private PlaceOrderResult placeOrderResult;
}
