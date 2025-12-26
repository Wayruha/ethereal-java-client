package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.wayruha.ethereal.dto.*;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfo {
  private String id;
  private String clientOrderId;
  private OrderType type;
  private BigDecimal availableQuantity;
  private BigDecimal quantity;
  private OrderSide side;
  private String productId;
  private String subaccountId;
  private OrderStatus status;
  private Boolean reduceOnly;
  private Boolean close; // Order closes the entire current position
  private Long updatedAt;
  private Long createdAt;
  private String sender;
  private BigDecimal price;
  private BigDecimal filled;
  private BigDecimal stopPrice;
  private StopType stopType;
  private StopPriceType stopPriceType;
  private TimeInForce timeInForce;
  private Long expiresAt;
  private Boolean postOnly;
  private GroupContingencyType groupContingencyType; // Type of OTOCO relationship
  private String groupId;
}
