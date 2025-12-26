package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.wayruha.ethereal.dto.EngineType;
import trade.wayruha.ethereal.dto.ProductStatus;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {
  private String id; //also referred as "productId"
  private String ticker;
  private String displayTicker;
  private String baseTokenAddress;
  private String quoteTokenAddress;
  private String baseTokenName;
  private String quoteTokenName;
  private EngineType engineType;
  private Long onchainId;
  private ProductStatus status;
  private String blockNumber;
  private BigDecimal cumulativeFundingUsd;
  private Long createdAt;
  private Long fundingUpdatedAt;
  private BigDecimal minQuantity;
  private BigDecimal lotSize;
  private BigDecimal tickSize;
  private BigDecimal makerFee;
  private BigDecimal takerFee;
  private BigDecimal maxQuantity;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;
  private BigDecimal volume24h;
  private Integer maxLeverage;
  private Long pythFeedId;
  private BigDecimal fundingRate1h;
  private BigDecimal openInterest;
  private BigDecimal maxOpenInterestUsd;
  private BigDecimal maxPositionNotionalUsd;
}
