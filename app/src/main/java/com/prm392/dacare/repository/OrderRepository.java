package com.prm392.dacare.repository;

import com.prm392.dacare.payload.response.OrderDetail.OrderDetailResponse;
import com.prm392.dacare.payload.response.OrderResponse.OrderResponse;
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

    public void getOrderDetail(String id, Callback<OrderDetailResponse> callback) {
        apiService.getOrderById(id).enqueue(callback);
    }
}
