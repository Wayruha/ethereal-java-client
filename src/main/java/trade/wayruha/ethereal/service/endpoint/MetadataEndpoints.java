package trade.wayruha.ethereal.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trade.wayruha.ethereal.dto.response.PageableResponse;
import trade.wayruha.ethereal.dto.response.ProductInfo;
import trade.wayruha.ethereal.dto.response.ProductsMarketPriceResponse;

import java.util.List;

public interface MetadataEndpoints {

  @GET("v1/product")
  Call<PageableResponse<ProductInfo>> getProducts(@Query("cursor") String cursor);

  @GET("v1/product/market-price")
  Call<ProductsMarketPriceResponse> getProductsMarketPriceByIds(@Query("productIds") List<String> productIds);

  @GET("v1/product/{id}")
  Call<ProductInfo> getProductById(@Path("id") String id);
}
