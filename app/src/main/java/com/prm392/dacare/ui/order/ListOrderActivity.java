package com.prm392.dacare.ui.order;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.OrderAdapter;
import com.prm392.dacare.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        // Sample data initialization
        orderList = new ArrayList<>();
        // You need to create Order objects based on your model, for example:
        Order sampleOrder = new Order();
        sampleOrder.setId("123456");
        sampleOrder.setOrderDate(new Date());
//        User user = new User();
//        user.setName("John Doe");
//        sampleOrder.setCustomer(user);
        sampleOrder.setAmount(250000);
        sampleOrder.setStatus("Completed");
        sampleOrder.setPaid(true);
        // Optionally set reasonCancel if order is cancelled.
        orderList.add(sampleOrder);

        // Create and set the adapter
        orderAdapter = new OrderAdapter(orderList);
        rvOrders.setAdapter(orderAdapter);
    }
}