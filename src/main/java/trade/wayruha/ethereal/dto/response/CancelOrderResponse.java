package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderResponse {
  private String id;
  private String clientOrderId;
  @JsonProperty("result")
  private CancelOrderResult cancelOrderResult;
}
