package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenBalance {
  private final String subaccountId;
  private final String tokenId;
  private final String tokenAddress;
  private final String tokenName;
  private final BigDecimal amount;

  /**
   * Portion of balance transferrable in native units
   */
  private final BigDecimal available;

  /**
   * Portion of balance non-transferrable in native units
   */
  private final BigDecimal totalUsed;
  private final Long updatedAt;
}
