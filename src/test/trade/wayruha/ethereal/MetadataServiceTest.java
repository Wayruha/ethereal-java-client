package trade.wayruha.ethereal;

import lombok.SneakyThrows;
import trade.wayruha.ethereal.dto.response.ProductInfo;
import trade.wayruha.ethereal.dto.response.ProductMarketPriceInfo;
import trade.wayruha.ethereal.dto.response.RpcConfigResponse;
import trade.wayruha.ethereal.service.MetadataService;
import trade.wayruha.ethereal.service.RpcService;

import java.util.List;
import java.util.stream.Collectors;

public class MetadataServiceTest {

  private static MetadataService metadataService;
  private static RpcService rpcService;

  @SneakyThrows
  public static void main(String[] args) {
    EtherealConfig etherealConfig = new EtherealConfig(true);
    metadataService = new MetadataService(etherealConfig);
    rpcService = new RpcService(etherealConfig);

    getProducts();
    getProductsMarketPricesByIds();
    getProductById();
    getRpcConfig();
  }

  private static void getProducts() {
    final List<ProductInfo> result = metadataService.getAllProductsInfo();
    result.forEach(System.out::println);
    assert !result.isEmpty();
  }

  private static void getProductsMarketPricesByIds() {
    final List<ProductInfo> productInfos = metadataService.getAllProductsInfo();
    final List<ProductMarketPriceInfo> result = metadataService.getProductsMarketPricesByProductIds(productInfos.stream().map(ProductInfo::getId).collect(Collectors.toList()));
    result.forEach(System.out::println);
    assert !result.isEmpty();
  }

  private static void getProductById() {
    final List<ProductInfo> productInfos = metadataService.getAllProductsInfo();
    final ProductInfo result = metadataService.getProductsInfoByProductId(productInfos.get(0).getId());
    System.out.println(result);
    assert !(result == null);
  }

  private static void getRpcConfig() {
    final RpcConfigResponse rpcConfig = rpcService.getRpcConfig();
    System.out.println(rpcConfig);
    assert !(rpcConfig == null);
  }
}
