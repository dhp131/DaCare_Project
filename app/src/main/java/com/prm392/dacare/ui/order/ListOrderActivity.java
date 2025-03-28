package com.prm392.dacare.ui.order;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.OrderAdapter;
import com.prm392.dacare.model.Order;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOrderActivity extends AppCompatActivity {

    private RecyclerView rvOrders;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));

        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList);
        rvOrders.setAdapter(orderAdapter);

        fetchOrdersFromAPI();
    }

    private void fetchOrdersFromAPI() {
        com.prm392.dacare.repository.OrderRepository orderRepository = new com.prm392.dacare.repository.OrderRepository();
        orderRepository.getOrders(new Callback<com.prm392.dacare.payload.response.OrderResponse.OrderResponse>() {
            @Override
            public void onResponse(Call<com.prm392.dacare.payload.response.OrderResponse.OrderResponse> call, Response<com.prm392.dacare.payload.response.OrderResponse.OrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Order> orders = response.body().getOrders().getData();
                    if (orders != null && !orders.isEmpty()) {
                        orderAdapter.updateOrders(orders);
                    } else {
                        Toast.makeText(ListOrderActivity.this, "No orders found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ListOrderActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.prm392.dacare.payload.response.OrderResponse.OrderResponse> call, Throwable t) {
                Toast.makeText(ListOrderActivity.this, "API error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
