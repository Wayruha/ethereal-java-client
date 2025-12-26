package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSSubaccountLiquidationSubscription extends WSSubscription {
  public final static String SUBACCOUNT_LIQUIDATION_EVENT_TYPE = "SubaccountLiquidation";

  private final String subaccountId;

  public WSSubaccountLiquidationSubscription(String subaccountId) {
    super(SUBACCOUNT_LIQUIDATION_EVENT_TYPE);
    this.subaccountId = subaccountId;
  }
}
