package com.prm392.dacare.service;

import com.prm392.dacare.model.User;
import com.prm392.dacare.payload.request.LoginRequest;
import com.prm392.dacare.payload.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
