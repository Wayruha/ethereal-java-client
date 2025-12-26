package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.wayruha.ethereal.dto.OrderSide;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionInfo {
  private String id;

  /**
   * Current cost of the position in USD
   */
  private BigDecimal cost;
  private BigDecimal size;

  /**
   * Charged but unapplied funding on position, negative if paid
   */
  private BigDecimal fundingUsd;

  /**
   * Fees accrued in USD
   */
  private BigDecimal realizedPnl;

  /**
   * Cumulative USD value of all position increases
   */
  private BigDecimal totalIncreaseNotional;

  /**
   * Cumulative quantity of all position increases
   */
  private BigDecimal totalIncreaseQuantity;

  /**
   * Cumulative USD value of all position decreases
   */
  private BigDecimal totalDecreaseNotional;

  /**
   * Cumulative quantity of all position decreases
   */
  private OrderSide side;
  private String productId;
  private Long updatedAt;
  private Long createdAt;
  private Boolean isLiquidated;
  private BigDecimal liquidationPrice;
}
