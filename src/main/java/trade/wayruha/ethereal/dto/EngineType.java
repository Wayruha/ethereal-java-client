package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EngineType {
  PERP(0),
  ONE(1); // mentioned in api, but still no info. Presumably SPOT

  @JsonValue
  private final int type;
}
