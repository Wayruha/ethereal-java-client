package trade.wayruha.ethereal.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.ethereal.dto.TakerSide;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSTradeFillUpdate {
  private String productId;
  @JsonProperty("data")
  private List<TradeFill> items;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TradeFill {
    private String id;
    private BigDecimal price;
    private BigDecimal filled;
    private TakerSide takerSide;
    private Long createdAt;
  }
}
