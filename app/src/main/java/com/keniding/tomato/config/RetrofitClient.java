package com.keniding.tomato.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Credentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:8800/api/";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "82293d47-bd09-447e-944c-a8897565241e";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okhttp3.Interceptor authInterceptor = chain -> {
                okhttp3.Request original = chain.request();

                okhttp3.Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                        .method(original.method(), original.body());

                okhttp3.Request request = requestBuilder.build();
                return chain.proceed(request);
            };

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(authInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
