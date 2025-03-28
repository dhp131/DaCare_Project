package com.prm392.dacare.repository;

import android.util.Log;

import com.prm392.dacare.model.Routine;
import com.prm392.dacare.payload.response.RoutineResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import retrofit2.Callback;

public class RoutineRepository {

    private APIService apiService;

    // Constructor khởi tạo APIService từ Retrofit client
    public RoutineRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    // Phương thức lấy routine theo loại da
    public void getRoutineBySkinType(String skinTypeId, Callback<Routine> callback) {
        Log.i("RoutineRepository", "Calling API to get routine for skinTypeId: " + skinTypeId);
        // Gọi API để lấy routine cho loại da theo skinTypeId
        apiService.getRoutineBySkinType(skinTypeId).enqueue(callback);
    }
}
