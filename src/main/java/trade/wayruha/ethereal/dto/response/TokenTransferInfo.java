package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.wayruha.ethereal.dto.*;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenTransferInfo {
  private String id;
  private String initiatedBlockNumber;
  private String finalizedBlockNumber;
  private TokenTransferStatus status;
  private String subaccountId;
  private String tokenName;
  private String tokenAddress;
  private TokenTransferType type;
  private BigDecimal amount;

  /**
   * LayerZero destination address
   */
  private String lzDestinationAddress;

  /**
   * LayerZero destination endpoint ID for the transfer (if withdraw)
   */
  private String lzDestinationEid;
  private BigDecimal fee;
  private Long createdAt;
  private String initiatedTransactionHash;
  private String finalizedTransactionHash;
}
