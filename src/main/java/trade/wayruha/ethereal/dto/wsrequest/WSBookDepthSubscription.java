package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public class WSBookDepthSubscription extends WSSubscription {
  public final static String BOOK_DEPTH_EVENT_TYPE = "BookDepth";

  private final String productId;

  public WSBookDepthSubscription(String productId) {
    super(BOOK_DEPTH_EVENT_TYPE);
    this.productId = productId;
  }
}
