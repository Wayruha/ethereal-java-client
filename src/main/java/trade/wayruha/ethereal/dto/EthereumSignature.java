package trade.wayruha.ethereal.dto;

import lombok.Value;
import org.web3j.utils.Numeric;

@Value
public class EthereumSignature {
  String r;
  String s;
  int v;

  public String toHexSignature() {
    String rClean = Numeric.cleanHexPrefix(r);
    String sClean = Numeric.cleanHexPrefix(s);

    String vHex;
    if (v == 27) {
      vHex = "1b";
    } else if (v == 28) {
      vHex = "1c";
    } else {
      throw new IllegalArgumentException("Invalid v value: " + v);
    }

    return "0x" + rClean + sClean + vHex;
  }
}
