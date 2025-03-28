package com.prm392.dacare.ui.routine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.payload.response.RoutineResponse;
import com.prm392.dacare.repository.RoutineRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineViewModel extends ViewModel {

    private RoutineRepository routineRepository;
    private MutableLiveData<RoutineResponse> routineLiveData;
    private MutableLiveData<String> errorLiveData;

    public RoutineViewModel() {
        routineRepository = new RoutineRepository();
        routineLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    // Phương thức lấy routine từ Repository
    public void getRoutineBySkinType(String skinTypeId) {
        routineRepository.getRoutineBySkinType(skinTypeId, new Callback<RoutineResponse>() {
            @Override
            public void onResponse(Call<RoutineResponse> call, Response<RoutineResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        routineLiveData.setValue(response.body());
                    } else {
                        errorLiveData.setValue("No data found for the given skin type");
                    }
                } else {
                    errorLiveData.setValue("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RoutineResponse> call, Throwable t) {
                errorLiveData.setValue("Failed to load routine: " + t.getMessage());
            }
        });
    }

    // Getter cho routineLiveData và errorLiveData
    public LiveData<RoutineResponse> getRoutineLiveData() {
        return routineLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
}
