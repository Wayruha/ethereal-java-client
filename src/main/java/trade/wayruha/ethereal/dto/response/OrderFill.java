package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.wayruha.ethereal.dto.OrderSide;
import trade.wayruha.ethereal.dto.OrderType;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderFill {
  private String id;
  private String orderId;
  private String clientOrderId;
  private BigDecimal price;
  private BigDecimal filled;
  private OrderType type;
  private OrderSide side;
  private Boolean reduceOnly;
  private BigDecimal feeUsd;
  private Boolean isMaker;
  private String productId;
  private String subaccountId;
  private Long createdAt;
}
