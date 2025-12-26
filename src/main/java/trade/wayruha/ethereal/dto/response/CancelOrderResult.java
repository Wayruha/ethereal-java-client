package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CancelOrderResult {
  ALREADY_CANCELED("AlreadyCanceled"),
  ALREADY_EXPIRED("AlreadyExpired"),
  ALREADY_FILLED("AlreadyFilled"),
  NOT_FOUND("NotFound"),
  OK("Ok"),
  NONCE_ALREADY_USED("NonceAlreadyUsed"),
  UNKNOWN("Unknown");

  @JsonValue
  private final String code;
}
