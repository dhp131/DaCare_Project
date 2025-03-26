package com.prm392.dacare.repository;

import com.prm392.dacare.model.Order;
import com.prm392.dacare.payload.response.OrderResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import retrofit2.Callback;

public class OrderRepository {
    private APIService apiService;
    public OrderRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void getOrders( Callback<OrderResponse> callback) {
        apiService.getOrder().enqueue(callback);
    }
}
