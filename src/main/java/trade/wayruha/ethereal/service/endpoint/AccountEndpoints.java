package trade.wayruha.ethereal.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trade.wayruha.ethereal.dto.response.PageableResponse;
import trade.wayruha.ethereal.dto.response.SubaccountInfo;
import trade.wayruha.ethereal.dto.response.TokenBalance;

public interface AccountEndpoints {

  /**
   * @param sender Address of the sender
   */
  @GET("v1/subaccount")
  Call<PageableResponse<SubaccountInfo>> getSubaccounts(@Query("sender") String sender, @Query("cursor") String cursor);

  @GET("v1/subaccount/{id}")
  Call<SubaccountInfo> getSubaccountById(@Path("id") String subaccountId);

  @GET("v1/subaccount/balance")
  Call<PageableResponse<TokenBalance>> getSubaccountBalance(@Query("subaccountId") String subaccountId, @Query("cursor") String cursor);
}