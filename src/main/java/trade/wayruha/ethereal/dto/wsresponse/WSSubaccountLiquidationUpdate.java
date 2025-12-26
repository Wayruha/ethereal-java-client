package trade.wayruha.ethereal.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSSubaccountLiquidationUpdate {
  private String subaccountId;
  private Long liquidatedAt;
}
