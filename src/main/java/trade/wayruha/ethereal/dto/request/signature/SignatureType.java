package trade.wayruha.ethereal.dto.request.signature;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignatureType {
  LINK_SIGNER("LinkSigner"),
  TRADE_ORDER("TradeOrder"),
  INITIATE_WITHDRAW("InitiateWithdraw"),
  REVOKE_LINKED_SIGNER("RevokeLinkedSigner"),
  EIP_712_AUTH("EIP712Auth"),
  CANCEL_ORDER("CancelOrder"),
  REFRESH_LINKED_SIGNER("RefreshLinkedSigner");

  @JsonValue
  private final String type;
}
