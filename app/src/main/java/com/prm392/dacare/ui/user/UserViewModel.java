package com.prm392.dacare.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.payload.response.UserRes;
import com.prm392.dacare.payload.response.UserResponse;
import com.prm392.dacare.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final MutableLiveData<UserRes> user = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public UserViewModel() {
        userRepository = new UserRepository();
        loadUser();
    }

    private void loadUser(){
        isLoading.setValue(true);
        userRepository.getUser(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    user.setValue(response.body().getUser());
                    isLoading.setValue(false);
                } else {
                    message.setValue(response.message());
                    isLoading.setValue(false);
                }

            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                isLoading.setValue(false);
                message.setValue(t.getMessage());
            }
        });
    }


    public LiveData<UserRes> getUser() {
        return user;
    }

    public LiveData<String> getMessage() {
        return message;
    }
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}