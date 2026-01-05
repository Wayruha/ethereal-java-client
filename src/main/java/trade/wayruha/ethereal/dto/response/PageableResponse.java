package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResponse<T> {
  @JsonProperty("hasNext")
  private boolean hasNext;
  @JsonProperty("nextCursor")
  private String nextCursor;
  @JsonProperty("data")
  private List<T> items;
}
