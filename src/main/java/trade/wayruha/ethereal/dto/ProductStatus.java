package trade.wayruha.ethereal.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
  PENDING("PENDING"),
  ACTIVE("ACTIVE");

  @JsonValue
  private final String name;
}
