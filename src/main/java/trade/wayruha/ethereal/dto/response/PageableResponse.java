package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResponse {
  @JsonProperty("hasNext")
  private boolean hasNext;
  @JsonProperty("nextCursor")
  private String nextCursor;
}
