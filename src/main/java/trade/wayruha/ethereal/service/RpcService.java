package trade.wayruha.ethereal.service;

import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.dto.response.RpcConfigResponse;
import trade.wayruha.ethereal.service.endpoint.RpcEndpoints;

public class RpcService extends ServiceBase {
  private final RpcEndpoints rpcApi;

  public RpcService(EtherealConfig config) {
    super(config);
    this.rpcApi = createService(RpcEndpoints.class);
  }

  public RpcConfigResponse getRpcConfig() {
    return client.executeSync(rpcApi.getRpcConfig());
  }
}
