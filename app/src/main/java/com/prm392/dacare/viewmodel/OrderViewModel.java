package com.prm392.dacare.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.Order;
import com.prm392.dacare.payload.response.OrderResponse.OrderResponse;
import com.prm392.dacare.repository.OrderRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<List<Order>> orders;
    private OrderRepository orderRepository;

    public OrderViewModel() {
        orderRepository = new OrderRepository();
        orders = new MutableLiveData<>();
        loadOrders();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    private void loadOrders() {
        orderRepository.getOrders(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orders.postValue(response.body().getOrders().getData());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("OrderViewModel", "Failed to load orders", t);
            }
        });
    }
}

