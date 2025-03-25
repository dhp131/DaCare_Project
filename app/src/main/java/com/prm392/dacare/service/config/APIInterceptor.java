package com.prm392.dacare.service.config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class APIInterceptor implements Interceptor {
    private String token;

    public APIInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Accept", "application/json");

        if (token != null && !token.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }

        Request newRequest = requestBuilder.build();
        Response response = chain.proceed(newRequest);

        if (response.code() == 401) {
            //Handle token expired
        }

        return response;
    }
}
