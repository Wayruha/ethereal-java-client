package trade.wayruha.ethereal.util;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.StructuredData;
import org.web3j.crypto.StructuredDataEncoder;
import org.web3j.utils.Numeric;
import trade.wayruha.ethereal.dto.EthereumSignature;
import trade.wayruha.ethereal.dto.request.signature.SignatureType;
import trade.wayruha.ethereal.dto.response.RpcConfigResponse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public final class TransactionSignatureUtil {

  public static EthereumSignature signEip712(
      String privateKeyHex,
      RpcConfigResponse.Domain domain,
      SignatureType primaryType,
      HashMap<String, List<StructuredData.Entry>> types,
      LinkedHashMap<String, Object> message
  ) {
    // adding domain eip712 type
    types.put("EIP712Domain", List.of(
        new StructuredData.Entry("name", "string"),
        new StructuredData.Entry("version", "string"),
        new StructuredData.Entry("chainId", "uint256"),
        new StructuredData.Entry("verifyingContract", "address")
    ));

    // adding domain data
    final StructuredData.EIP712Domain eip712Domain =
        new StructuredData.EIP712Domain(
            domain.getName(),
            domain.getVersion(),
            String.valueOf(domain.getChainId()),
            domain.getVerifyingContract(),
            null
        );

    final ECKeyPair keyPair = ECKeyPair.create(Numeric.toBigInt(privateKeyHex));

    // constructing final message
    final StructuredData.EIP712Message eip712Message =
        new StructuredData.EIP712Message(
            types,
            primaryType.getType(),
            message,
            eip712Domain
        );

    // hashing full-ready message
    final byte[] hash = new StructuredDataEncoder(eip712Message)
        .hashStructuredData();

    final Sign.SignatureData sig = Sign.signMessage(hash, keyPair, false);

    return splitSig(sig);
  }

  private static EthereumSignature splitSig(Sign.SignatureData sig) {
    final String r = Numeric.toHexString(sig.getR());
    final String s = Numeric.toHexString(sig.getS());
    final int v = sig.getV()[0];

    // Adjust v value
    if (v == 27 || v == 28) {
      return new EthereumSignature(r, s, v);
    } else if (v == 0 || v == 1) {
      return new EthereumSignature(r, s, v + 27);
    } else {
      throw new IllegalArgumentException("Invalid v value: " + v);
    }
  }
}