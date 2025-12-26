package trade.wayruha.ethereal.service;

import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.response.ProductInfo;
import trade.wayruha.ethereal.dto.response.ProductMarketPriceInfo;
import trade.wayruha.ethereal.dto.response.ProductsInfoResponse;
import trade.wayruha.ethereal.service.endpoint.MetadataEndpoints;

import java.util.ArrayList;
import java.util.List;

public class MetadataService extends ServiceBase {
  private final MetadataEndpoints metadataApi;

  public MetadataService(EtherealConfig config) {
    super(config);
    this.metadataApi = createService(MetadataEndpoints.class);
  }

  public ProductsInfoResponse getProductsInfo(String cursor) {
    return client.executeSync(metadataApi.getProducts(cursor));
  }

  /**
   * Same as getProductsInfo but fetches all pages
   *
   * @return List of ProductInfo
   */
  public List<ProductInfo> getAllProductsInfo() {
    String nextCursor = null;
    boolean hasNext;
    final List<ProductInfo> productsResult = new ArrayList<>(List.of());
    do {
      final ProductsInfoResponse productsInfoResponse = getProductsInfo(nextCursor);
      nextCursor = productsInfoResponse.getNextCursor();
      hasNext = productsInfoResponse.isHasNext();

      final List<ProductInfo> newProducts = productsInfoResponse.getItems();
      productsResult.addAll(newProducts);
    } while (hasNext);
    return productsResult;
  }

  public List<ProductMarketPriceInfo> getProductsMarketPricesByProductIds(List<String> productIds) {
    return client.executeSync(metadataApi.getProductsMarketPriceByIds(productIds)).getItems();
  }

  public ProductInfo getProductsInfoByProductId(String productId) {
    return client.executeSync(metadataApi.getProductById(productId));
  }
}
