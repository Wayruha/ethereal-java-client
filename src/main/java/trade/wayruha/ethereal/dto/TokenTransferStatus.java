package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenTransferStatus {
  SUBMITTED("SUBMITTED"),
  PENDING("PENDING"),
  COMPLETED("COMPLETED"),
  REJECTED("REJECTED");

  @JsonValue
  private final String name;
}
