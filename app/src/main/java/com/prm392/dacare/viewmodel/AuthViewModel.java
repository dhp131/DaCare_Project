package com.prm392.dacare.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.payload.response.LoginResponse;
import com.prm392.dacare.repository.AuthRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    private final MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();
    private final AuthRepository authRepository = new AuthRepository();

    public LiveData<LoginResponse> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        authRepository.login(email, password, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResult.setValue(response.body());
                } else {
                    loginResult.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResult.setValue(null);
            }
        });
    }
}
