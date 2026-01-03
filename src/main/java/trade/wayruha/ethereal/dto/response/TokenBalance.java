package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenBalance {
  private String subaccountId;
  private String tokenId;
  private String tokenAddress;
  private String tokenName;
  private BigDecimal amount;

  /**
   * Portion of balance transferrable in native units
   */
  private BigDecimal available;

  /**
   * Portion of balance non-transferrable in native units
   */
  private BigDecimal totalUsed;
  private Long updatedAt;
}
