package com.prm392.dacare.service;

import com.prm392.dacare.service.config.APIInterceptor;
import com.prm392.dacare.utils.SharedPreferencesUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://beaute-project-be-1.onrender.com/api/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            String token = SharedPreferencesUtil.getAccessToken();
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new APIInterceptor(token))
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
