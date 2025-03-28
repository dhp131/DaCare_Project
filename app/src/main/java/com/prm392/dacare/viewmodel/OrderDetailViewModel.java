package com.prm392.dacare.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.prm392.dacare.model.Order;
import com.prm392.dacare.model.OrderExtend;
import com.prm392.dacare.payload.response.OrderDetail.OrderDetailResponse;
import com.prm392.dacare.repository.OrderRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailViewModel extends ViewModel {

    private MutableLiveData<OrderExtend> orderDetail;
    private OrderRepository orderRepository;

    public OrderDetailViewModel() {
        orderRepository = new OrderRepository();
        orderDetail = new MutableLiveData<>();
    }

    public LiveData<OrderExtend> getOrderDetail() {
        return orderDetail;
    }

    /**
     * Loads the order detail for the given id.
     *
     * @param id The ID of the order.
     */
    public void loadOrderDetail(String id) {
        try {
            orderRepository.getOrderDetail(id, new Callback<OrderDetailResponse>() {
                @Override
                public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            orderDetail.postValue(response.body().getOrder().getOrder());
                        } else {
                            Log.e("OrderDetailViewModel", "Failed to load order detail: " + response.message());
                        }
                    } catch (Exception e) {
                        Log.e("OrderDetailViewModel", "Error processing order detail response", e);
                    }
                }

                @Override
                public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                    Log.e("OrderDetailViewModel", "Failed to load order detail", t);
                }
            });
        } catch (Exception e) {
            Log.e("OrderDetailViewModel", "Error calling getOrderDetail", e);
        }
    }

}
