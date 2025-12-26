package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeInForce {
  GTD("GTD"),
  IOC("IOC"),
  FOK("FOK");

  @JsonValue
  private final String name;
}
