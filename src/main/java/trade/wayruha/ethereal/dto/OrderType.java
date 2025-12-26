package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
  MARKET("MARKET"),
  LIMIT("LIMIT");

  @JsonValue
  private final String name;
}
