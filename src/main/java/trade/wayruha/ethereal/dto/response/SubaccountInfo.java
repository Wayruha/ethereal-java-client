package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountInfo {
  /**
   * Id representing the registered subaccount
   */
  private final String id;

  /**
   * Bytes32 encoded subaccount name
   */
  private final String name;

  /**
   * Address of the account which registered the subaccount (non-checksummed)
   */
  private final String account;

  /**
   * Block number this subaccount was registered on
   */
  private final String registeredBlockNumber;
  private final Long createdAt;
}
