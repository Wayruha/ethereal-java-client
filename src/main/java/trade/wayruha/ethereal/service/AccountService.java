package trade.wayruha.ethereal.service;

import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.response.PageableResponse;
import trade.wayruha.ethereal.dto.response.SubaccountInfo;
import trade.wayruha.ethereal.dto.response.TokenBalance;
import trade.wayruha.ethereal.service.endpoint.AccountEndpoints;

import java.util.ArrayList;
import java.util.List;

public class AccountService extends ServiceBase {
  private final AccountEndpoints accountApi;

  public AccountService(EtherealConfig config) {
    super(config);
    this.accountApi = createService(AccountEndpoints.class);
  }

  public PageableResponse<SubaccountInfo> getSubaccounts(String cursor) {
    return client.executeSync(accountApi.getSubaccounts(getConfig().getPublicKey(), cursor));
  }

  public SubaccountInfo getSubaccountById(String subaccountId) {
    return client.executeSync(accountApi.getSubaccountById(subaccountId));
  }

  public PageableResponse<TokenBalance> getSubaccountBalance(String subaccountId, String cursor) {
    return client.executeSync(accountApi.getSubaccountBalance(subaccountId, cursor));
  }

  public List<TokenBalance> getAllSubaccountBalances(String subaccountId) {
    String cursor = null;
    boolean nextCursor = false;

    final List<TokenBalance> result = new ArrayList<>();
    do {
      final PageableResponse<TokenBalance> balance = getSubaccountBalance(subaccountId, cursor);
      result.addAll(balance.getItems());
      cursor = balance.getNextCursor();
      nextCursor = balance.isHasNext();
    } while (nextCursor);
    return result;
  }
}
