package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSMarketPriceSubscription extends WSSubscription {
  public final static String MARKET_PRICE_EVENT_TYPE = "MarketPrice";

  private final String productId;

  public WSMarketPriceSubscription(String productId) {
    super(MARKET_PRICE_EVENT_TYPE);
    this.productId = productId;
  }
}
