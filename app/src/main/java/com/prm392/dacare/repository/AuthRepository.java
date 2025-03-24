package com.prm392.dacare.repository;

import com.prm392.dacare.payload.request.LoginRequest;
import com.prm392.dacare.payload.response.LoginResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;
import retrofit2.Callback;


public class AuthRepository {
    private APIService apiService;

    public AuthRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void login(String email, String password, Callback<LoginResponse> callback) {
        apiService.login(new LoginRequest(email, password)).enqueue(callback);
    }
}
