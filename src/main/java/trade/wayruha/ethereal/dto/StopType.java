package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StopType {
  TAKE_PROFIT(0),
  STOP_LOSS(1);

  @JsonValue
  private final int type;
}
