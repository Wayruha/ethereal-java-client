package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenTransferType {
  DEPOSIT("DEPOSIT"),
  WITHDRAW("WITHDRAW");

  @JsonValue
  private final String name;
}
