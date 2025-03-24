package com.prm392.dacare.repository;

import com.prm392.dacare.model.User;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import java.util.List;

import javax.security.auth.callback.Callback;

public class UserRepository {
    private APIService apiService;

    public UserRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

}
