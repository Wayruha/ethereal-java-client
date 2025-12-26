package trade.wayruha.ethereal.service;

import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.response.AllSubaccountsResponse;
import trade.wayruha.ethereal.dto.response.SubaccountBalanceResponse;
import trade.wayruha.ethereal.dto.response.SubaccountInfo;
import trade.wayruha.ethereal.service.endpoint.AccountEndpoints;

public class AccountService extends ServiceBase {
  private final AccountEndpoints accountApi;

  public AccountService(EtherealConfig config) {
    super(config);
    this.accountApi = createService(AccountEndpoints.class);
  }

  public AllSubaccountsResponse getSubaccounts(String accountAddress, String cursor) {
    return client.executeSync(accountApi.getSubaccounts(accountAddress, cursor));
  }

  public SubaccountInfo getSubaccountById(String subaccountId) {
    return client.executeSync(accountApi.getSubaccountById(subaccountId));
  }

  public SubaccountBalanceResponse getSubaccountBalance(String subaccountId) {
    return client.executeSync(accountApi.getSubaccountBalance(subaccountId));
  }
}
