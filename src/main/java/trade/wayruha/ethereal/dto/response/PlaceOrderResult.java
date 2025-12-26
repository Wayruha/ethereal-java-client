package trade.wayruha.ethereal.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceOrderResult {
  ACCOUNT_SUSPENDED("AccountSuspended"),
  CAUSES_IMMEDIATE_LIQUIDATION("CausesImmediateLiquidation"),
  DUPLICATE_SAME_SIDE_OCO("DuplicateSameSideOco"),
  IMMEDIATE_MATCH_POST_ONLY("ImmediateMatchPostOnly"),
  INSUFFICIENT_BALANCE("InsufficientBalance"),
  LIQUIDATION_ERROR("LiquidationError"),
  MARKET_ORDER_REACHED_MAX_SLIPPAGE("MarketOrderReachedMaxSlippage"),
  OCO_FILLED("OcoFilled"),
  OK("Ok"),
  OPEN_VALUE_CAP_EXCEEDED("OpenValueCapExceeded"),
  ORDER_INCREASES_POSITION("OrderIncreasesPosition"),
  RISK_LIMIT_EXCEEDED("RiskLimitExceeded"),
  SIGNER_REVOKED("SignerRevoked"),
  TRIGGER_CANCELED_ERROR("TriggerCanceledError"),
  UNFILLED_FILL_OR_KILL("UnfilledFillOrKill"),
  UNFILLED_IMMEDIATE_OR_CANCEL("UnfilledImmediateOrCancel"),
  UNFILLED_MARKET_ORDER("UnfilledMarketOrder");

  @JsonValue
  private final String code;
}
