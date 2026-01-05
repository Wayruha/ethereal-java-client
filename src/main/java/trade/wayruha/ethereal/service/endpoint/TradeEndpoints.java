package trade.wayruha.ethereal.service.endpoint;

import retrofit2.Call;
import retrofit2.http.*;
import trade.wayruha.ethereal.dto.request.CancelOrderRequest;
import trade.wayruha.ethereal.dto.request.PlaceOrderRequest;
import trade.wayruha.ethereal.dto.response.*;

public interface TradeEndpoints {

  // ORDER
  @GET("v1/order")
  Call<PageableResponse<OrderInfo>> getOrders(@Query("subaccountId") String subaccountId, @Query("isWorking") Boolean isWorking, @Query("cursor") String cursor);

  @GET("v1/order/{id}")
  Call<OrderInfo> getOrderById(@Path("id") String id);

  @GET("v1/order/fill")
  Call<PageableResponse<OrderFill>> getOrderFills(@Query("subaccountId") String subaccountId);

  @POST("v1/order")
  Call<PlaceOrderResponse> placeOrder(@Body PlaceOrderRequest request);

  @POST("v1/order/cancel")
  Call<CancelOrdersResponse> cancelOrders(@Body CancelOrderRequest request);

  //POSITION
  @GET("v1/position")
  Call<PageableResponse<PositionInfo>> getPositions(@Query("subaccountId") String subaccountId, @Query("open") Boolean open, @Query("cursor") String cursor);

  @GET("v1/position/{id}")
  Call<PositionInfo> getPositionById(@Path("id") String id);
}