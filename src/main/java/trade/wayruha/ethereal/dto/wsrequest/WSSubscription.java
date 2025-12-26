package trade.wayruha.ethereal.dto.wsrequest;

import lombok.Getter;

@Getter
public abstract class WSSubscription {
  private final String type;

  public WSSubscription(String type) {
    this.type = type;
  }
}
