package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMarketPriceInfo {
  private String productId;
  private BigDecimal bestBidPrice;
  private BigDecimal bestAskPrice;
  private BigDecimal oraclePrice;
  private BigDecimal price24hAgo;
}
