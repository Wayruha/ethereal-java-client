package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountInfo {
  /**
   * Id representing the registered subaccount,
   * UUID
   */
  private String id;

  /**
   * Bytes32 encoded subaccount name
   */
  private String name;

  /**
   * Address of the account which registered the subaccount (non-checksummed)
   */
  private String account;

  /**
   * Block number this subaccount was registered on
   */
  private String registeredBlockNumber;
  private Long createdAt;
}
