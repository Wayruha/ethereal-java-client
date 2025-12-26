package trade.wayruha.ethereal.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WSBookDepthUpdate {
  private Long timestamp;
  private Long previousTimestamp;
  private String productId;

  @JsonDeserialize(contentUsing = OrderBookOrderDeserializer.class)
  private List<OrderBookOrder> asks;

  @JsonDeserialize(contentUsing = OrderBookOrderDeserializer.class)
  private List<OrderBookOrder> bids;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OrderBookOrder {
    private BigDecimal price;
    private BigDecimal quantity;
  }

  public static class OrderBookOrderDeserializer extends JsonDeserializer<OrderBookOrder> {

    @Override
    public OrderBookOrder deserialize(JsonParser p, DeserializationContext context) throws IOException {

      final JsonNode node = p.getCodec().readTree(p);

      final OrderBookOrder o = new OrderBookOrder();
      o.setPrice(new BigDecimal(node.get(0).asText()));
      o.setQuantity(new BigDecimal(node.get(1).asText()));
      return o;
    }
  }
}
