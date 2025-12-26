package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSTradeFillSubscription extends WSSubscription {
  public final static String TRADE_FILL_EVENT_TYPE = "TradeFill";

  private final String productId;

  public WSTradeFillSubscription(String productId) {
    super(TRADE_FILL_EVENT_TYPE);
    this.productId = productId;
  }
}
