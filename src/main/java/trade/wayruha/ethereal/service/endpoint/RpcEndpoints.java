package trade.wayruha.ethereal.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import trade.wayruha.ethereal.dto.response.RpcConfigResponse;

public interface RpcEndpoints {
  @GET("v1/rpc/config")
  Call<RpcConfigResponse> getRpcConfig();
}
