package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSOrderUpdateSubscription extends WSSubscription {
  public final static String ORDER_UPDATE_EVENT_TYPE = "OrderUpdate";

  private final String subaccountId;

  public WSOrderUpdateSubscription(String subaccountId) {
    super(ORDER_UPDATE_EVENT_TYPE);
    this.subaccountId = subaccountId;
  }
}
