package trade.wayruha.ethereal.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.config.ApiClient;

import static trade.wayruha.ethereal.config.Constant.DEFAULT_RECEIVING_WINDOW;

public abstract class ServiceBase {
  protected int receivingWindow = DEFAULT_RECEIVING_WINDOW;
  protected final ApiClient client;

  public ServiceBase(ApiClient client) {
    this.client = client;
  }

  public ServiceBase(EtherealConfig config) {
    this(new ApiClient(config));
  }

  public EtherealConfig getConfig() {
    return client.getConfig();
  }

  public <T> T createService(Class<T> apiClass) {
    return client.createService(apiClass);
  }

  protected ObjectMapper getObjectMapper(){
    return client.getConfig().getObjectMapper();
  }

  protected long now() {
    return System.currentTimeMillis();
  }
}
