package trade.wayruha.ethereal;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.SneakyThrows;
import trade.wayruha.ethereal.dto.response.*;
import trade.wayruha.ethereal.dto.wsresponse.WSBookDepthUpdate;
import trade.wayruha.ethereal.dto.wsresponse.WSSubaccountLiquidationUpdate;
import trade.wayruha.ethereal.dto.wsresponse.WSTradeFillUpdate;
import trade.wayruha.ethereal.service.AccountService;
import trade.wayruha.ethereal.service.MetadataService;
import trade.wayruha.ethereal.websocket.WebSocketCallback;
import trade.wayruha.ethereal.websocket.WebSocketClient;
import trade.wayruha.ethereal.websocket.WebSocketClientFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SocketIOTest {
  final static EtherealConfig etherealConfig = new EtherealConfig(false);
  final static WebSocketClientFactory factory = new WebSocketClientFactory(etherealConfig);
  final static MetadataService metadataService = new MetadataService(etherealConfig);
  final static AccountService accountService = new AccountService(etherealConfig);

  static final AllSubaccountsResponse subaccounts = accountService.getSubaccounts(null);
  static String subaccountId;

  @SneakyThrows
  public static void main(String[] args) {
    subaccountId = subaccounts.getItems().get(0).getId();
    orderBookTest();
    marketPriceTest();
    orderFillsTest();
    tradeFillsTest();
    orderUpdateTest();
    subaccountLiquidationTest();
    tokenTransferTest();
  }

  public static void orderBookTest() {
    final List<ProductInfo> productsInfo = metadataService.getAllProductsInfo();
    final Callback<WSBookDepthUpdate> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<WSBookDepthUpdate> orderBookSubscription = factory.orderBookSubscription(productsInfo.stream().map(ProductInfo::getId).collect(Collectors.toList()), callback);
    System.out.println(orderBookSubscription.reConnect());
  }

  public static void marketPriceTest() {
    final List<ProductInfo> productsInfo = metadataService.getAllProductsInfo();
    final Callback<ProductMarketPriceInfo> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<ProductMarketPriceInfo> marketPriceSubscription = factory.marketPriceSubscription(productsInfo.stream().map(ProductInfo::getId).collect(Collectors.toList()), callback);
  }

  public static void orderFillsTest() {
    final Callback<OrderFillsResponse> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<OrderFillsResponse> orderFillSubscription = factory.orderFillSubscription(subaccountId, callback);
  }

  public static void tradeFillsTest() {
    final List<ProductInfo> productsInfo = metadataService.getAllProductsInfo();
    final Callback<WSTradeFillUpdate> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<WSTradeFillUpdate> tradeFillSubscription = factory.tradeFillSubscription(productsInfo.stream().map(ProductInfo::getId).collect(Collectors.toList()), callback);
  }

  public static void orderUpdateTest() {
    final Callback<OrdersInfoResponse> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<OrdersInfoResponse> orderUpdateSubscription = factory.orderUpdateSubscription(subaccountId, callback);
  }

  public static void subaccountLiquidationTest() {
    final Callback<WSSubaccountLiquidationUpdate> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<WSSubaccountLiquidationUpdate> subaccountLiquidationSubscription = factory.subaccountLiquidationSubscription(subaccountId, callback);
  }

  public static void tokenTransferTest() {
    final Callback<TokenTransferResponse> callback = new Callback<>(new TypeReference<>() {
    });
    final WebSocketClient<TokenTransferResponse> subaccountLiquidationSubscription = factory.tokenTransferSubscription(subaccountId, callback);
  }

  @Getter
  static class Callback<T> implements WebSocketCallback<T> {
    final TypeReference<T> type;

    public Callback(TypeReference<T> type) {
      this.type = type;
    }

    @Override
    public void onResponse(T response) {
      System.out.println(response);
    }
  }

}
