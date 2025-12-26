package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSOrderFillSubscription extends WSSubscription {
  public static final String ORDER_FILL_EVENT_TYPE = "OrderFill";

  private final String subaccountId;

  public WSOrderFillSubscription(String subaccountId) {
    super("BookDepth");
    this.subaccountId = subaccountId;
  }
}
