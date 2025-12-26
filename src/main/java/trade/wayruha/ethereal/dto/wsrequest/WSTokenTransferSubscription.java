package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSTokenTransferSubscription extends WSSubscription {
  public final static String TOKEN_TRANSFER_EVENT_TYPE = "TokenTransfer";

  private final String subaccountId;

  public WSTokenTransferSubscription(String subaccountId) {
    super(TOKEN_TRANSFER_EVENT_TYPE);
    this.subaccountId = subaccountId;
  }
}
