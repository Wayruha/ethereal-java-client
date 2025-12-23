package trade.wayruha.ethereal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import trade.wayruha.ethereal.EtherealConfig;

public class RetrofitBuilder {

  public static Retrofit buildRetrofit(EtherealConfig configuration, OkHttpClient httpClient) {
    final ObjectMapper objectMapper = configuration.getObjectMapper();

    Retrofit.Builder builder = new Retrofit.Builder();
    builder.client(httpClient);
    builder.addConverterFactory(JacksonConverterFactory.create(objectMapper));
    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    builder.baseUrl(configuration.getHost());
    return builder.build();
  }
}
