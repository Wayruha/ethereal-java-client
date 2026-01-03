package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RpcConfigResponse {

  private Domain domain;
  private Map<String, String> signatureTypes;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Domain {
    private String name;
    private String version;
    private long chainId;
    private String verifyingContract;
  }

  @Data
  public static class SignatureField {
    private String type;
    private String name;
  }

  @JsonIgnore
  public Map<String, List<SignatureField>> getParsedSignatureTypes() {
    return signatureTypes.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            e -> parseSignature(e.getValue())
        ));
  }

  private List<SignatureField> parseSignature(String signature) {
    return Arrays.stream(signature.split(","))
        .map(String::trim)
        .map(this::parseField)
        .toList();
  }

  private SignatureField parseField(String field) {
    final String[] parts = field.split("\\s+");
    final SignatureField f = new SignatureField();
    f.setType(parts[0]);
    f.setName(parts[1]);
    return f;
  }
}
