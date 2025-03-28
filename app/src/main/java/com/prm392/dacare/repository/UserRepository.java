package com.prm392.dacare.repository;

import com.prm392.dacare.model.User;
import com.prm392.dacare.payload.response.UserResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;
import retrofit2.Callback;

import java.util.List;


public class UserRepository {
    private APIService apiService;

    public UserRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }
    public void getUser(Callback<UserResponse> callback){
        apiService.getUser().enqueue(callback);
    }
}
