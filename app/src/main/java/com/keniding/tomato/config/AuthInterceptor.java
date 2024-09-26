package com.keniding.tomato.config;

import androidx.annotation.NonNull;

import java.io.IOException;

public class AuthInterceptor implements okhttp3.Interceptor {
    @NonNull
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Request original = chain.request();

        okhttp3.Request.Builder requestBuilder = original.newBuilder();
        String token = AuthManager.getAuthToken();
        if (token != null) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }
        requestBuilder.method(original.method(), original.body());

        okhttp3.Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}