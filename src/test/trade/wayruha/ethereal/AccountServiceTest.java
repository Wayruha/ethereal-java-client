package trade.wayruha.ethereal;

import lombok.SneakyThrows;
import trade.wayruha.ethereal.dto.response.*;
import trade.wayruha.ethereal.service.AccountService;

public class AccountServiceTest {

  private static AccountService accountService;
  private static String subaccountUUID;

  @SneakyThrows
  public static void main(String[] args) {
    EtherealConfig etherealConfig = new EtherealConfig(false);
    accountService = new AccountService(etherealConfig);

    final PageableResponse<SubaccountInfo> subaccounts = accountService.getSubaccounts(null);
    subaccountUUID = subaccounts.getItems().get(0).getId();

    getBalances();
  }

  private static void getBalances() {
    final PageableResponse<TokenBalance> balance = accountService.getSubaccountBalance(subaccountUUID, null);
    balance.getItems().forEach(System.out::println);
    assert !balance.getItems().isEmpty();
  }
}
