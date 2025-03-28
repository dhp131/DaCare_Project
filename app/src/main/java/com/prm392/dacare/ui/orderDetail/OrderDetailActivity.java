package com.prm392.dacare.ui.orderDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.ProductInOrderAdapter;
import com.prm392.dacare.model.OrderExtend;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.viewmodel.OrderDetailViewModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private ProductInOrderAdapter orderDetailAdapter;
    private TextView tvOrderId, tvOrderDate, tvOrderStatus, tvOrderAmount, tvOrderPaid, tvReasonCancel;
    private TextView tvCustomerName, tvCustomerPhone, tvCustomerEmail;
    private OrderDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Initialize views
        rvProducts = findViewById(R.id.rvProducts);
        tvOrderId = findViewById(R.id.tvOrderId);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);
        tvOrderAmount = findViewById(R.id.tvOrderAmount);
        tvOrderPaid = findViewById(R.id.tvOrderPaid);
        tvReasonCancel = findViewById(R.id.tvReasonCancel);
        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvCustomerPhone = findViewById(R.id.tvCustomerPhone);
        tvCustomerEmail = findViewById(R.id.tvCustomerEmail);

        // Setup RecyclerView
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        orderDetailAdapter = new ProductInOrderAdapter(null); // Initially null, updated with data
        rvProducts.setAdapter(orderDetailAdapter);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);

        // Get order ID from intent
        String orderId = getIntent().getStringExtra("ORDER_ID");
        if (orderId != null) {
            // Load order details via ViewModel
            viewModel.loadOrderDetail(orderId);
            observeOrderDetail();
        } else {
            Toast.makeText(this, "No order ID provided", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void observeOrderDetail() {
        viewModel.getOrderDetail().observe(this, orderExtend -> {
            if (orderExtend != null) {
                setupOrderDetails(orderExtend);
            } else {
                Toast.makeText(this, "Failed to load order details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupOrderDetails(OrderExtend order) {
        // Set order basic information
        tvOrderId.setText("#" + order.get_id());
        tvOrderDate.setText(new SimpleDateFormat("dd MMM yyyy").format(order.getOrderDate()));
        tvOrderStatus.setText("Status: " + order.getStatus());
        tvOrderAmount.setText("\uD83D\uDCB0 Total: " + String.format("%,d", order.getAmount()) + " VND");
        tvOrderPaid.setText("Payment: " + (order.isPaid() ? "Paid" : "Unpaid"));

        // Set customer information
        if (order.getCustomerId() != null) {
            tvCustomerName.setText(order.getCustomerId().getName());
            tvCustomerPhone.setText("\uD83D\uDCDE Phone: " + order.getCustomerId().getPhone());
            tvCustomerEmail.setText("✉\uFE0F Email: " + order.getCustomerId().getEmail());
        }

        // Handle cancellation reason
        if (order.getReasonCancel() != null && !order.getReasonCancel().isEmpty()) {
            tvReasonCancel.setText("Reason: " + order.getReasonCancel());
            tvReasonCancel.setVisibility(View.VISIBLE);
        } else {
            tvReasonCancel.setVisibility(View.GONE);
        }

        // Update products list
        List<Product> products = order.getProducts();
        if (products != null && !products.isEmpty()) {
            orderDetailAdapter.setProductList(products);
        } else {
            Toast.makeText(this, "No products found in this order", Toast.LENGTH_SHORT).show();
        }
    }
}