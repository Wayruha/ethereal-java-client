package trade.wayruha.ethereal.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import trade.wayruha.ethereal.EtherealConfig;
import trade.wayruha.ethereal.exception.EtherealException;

import java.io.IOException;

import static java.util.Objects.nonNull;
import static trade.wayruha.ethereal.config.Constant.API_CLIENT_ERROR_MESSAGE_PARSE_EXCEPTION;

@Slf4j
public class ApiClient {
  @Getter
  private final EtherealConfig config;
  private final OkHttpClient httpClient;
  private final Retrofit retrofit;
  private final Buffer requestBodyBuffer;

  public ApiClient(EtherealConfig config) {
    this(config, new HttpClientBuilder(config));
  }

  public ApiClient(EtherealConfig config, HttpClientBuilder httpClientBuilder) {
    this.config = config;
    this.httpClient = httpClientBuilder.buildClient();
    this.retrofit = RetrofitBuilder.buildRetrofit(config, this.httpClient);
    this.requestBodyBuffer = new Buffer();
  }

  public WebSocket createWebSocket(Request request, WebSocketListener listener) {
    return httpClient.newWebSocket(request, listener);
  }

  public <T> T createService(final Class<T> service) {
    return this.retrofit.create(service);
  }

  public <T> T executeSync(Call<T> call) {
    final String rawRequestData = logRequestBody(call);
    try {
      final Response<T> response = call.execute();
      final T body = response.body();
      if (response.isSuccessful()) {
        return body;
      }
      final ResponseBody errBody = response.errorBody();
      String errorMessage = nonNull(errBody) ? errBody.string() : API_CLIENT_ERROR_MESSAGE_PARSE_EXCEPTION;
      throw new EtherealException(response.code() + ": " + errorMessage);
    } catch (Exception e) {
      log.error("Request failed. Request data: {},  response: {} ", rawRequestData, call.request(), e);
      throw new EtherealException(e.getMessage(), e);
    }
  }

  private <T> String logRequestBody(Call<T> call) {
    if (call.request().body() != null) {
      try {
        requestBodyBuffer.clear();
        call.request().body().writeTo(requestBodyBuffer);
        return requestBodyBuffer.readUtf8();
      } catch (IOException e) {
        log.error("Failed to read request body", e);
      }
    }
    return call.request().toString();
  }
}