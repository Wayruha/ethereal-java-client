package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// Type of OTOCO relationship
public enum GroupContingencyType {
  OTO(0), // Order-Triggers-Order
  OCO(1); // One-Cancels-Other

  @JsonValue
  private final int type;
}
