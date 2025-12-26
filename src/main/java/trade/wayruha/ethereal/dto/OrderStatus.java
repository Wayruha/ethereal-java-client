package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
  NEW("NEW"),
  PENDING("PENDING"),
  FILLED_PARTIAL("FILLED_PARTIAL"),
  FILLED("FILLED"),
  REJECTED("REJECTED"),
  CANCELED("CANCELED"),
  EXPIRED("EXPIRED");

  @JsonValue
  private final String name;
}
