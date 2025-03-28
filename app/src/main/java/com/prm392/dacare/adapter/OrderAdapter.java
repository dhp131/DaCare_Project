package com.prm392.dacare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Order;
import com.prm392.dacare.ui.orderDetail.OrderDetailActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        try {
            Order order = orderList.get(position);
            holder.bind(order);
            holder.itemView.setOnClickListener(v -> {
                try {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("ORDER_ID", order.get_id());
                    context.startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException("Error starting OrderDetailActivity for order: " + order.get_id(), e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Error binding order at position " + position, e);
        }
    }


    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView txtOrderId, txtOrderDate, txtCustomer, txtStatus, txtAmount, txtPaymentStatus, txtCancelReason;
        private ImageView imgCustomer;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
            txtCustomer = itemView.findViewById(R.id.txtCustomer);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtPaymentStatus = itemView.findViewById(R.id.txtPaymentStatus);
            txtCancelReason = itemView.findViewById(R.id.txtCancelReason);
            imgCustomer = itemView.findViewById(R.id.imgCustomer);
        }

        public void bind(Order order) {
            // Set order ID
            txtOrderId.setText("#" + order.get_id());

            // Format and set the order date
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            txtOrderDate.setText(sdf.format(order.getOrderDate()));

            // Set customer name (assumes that order.getCustomer() is populated)
            if (order.getCustomerId() != null) {
                txtCustomer.setText(order.getCustomerId());
            } else {
                txtCustomer.setText("Unknown Customer");
            }

            // Set order status
            txtStatus.setText(order.getStatus());

            // Format and set the order amount as currency
            txtAmount.setText(String.format(Locale.getDefault(), "%,d VND", order.getAmount()));

            // Set payment status based on isPaid flag
            txtPaymentStatus.setText(order.isPaid() ? "Paid" : "Unpaid");

            // Display cancel reason if status is "Cancel" and reasonCancel exists
            if ("Cancel".equalsIgnoreCase(order.getStatus()) && order.getReasonCancel() != null) {
                txtCancelReason.setVisibility(View.VISIBLE);
                txtCancelReason.setText("Reason: " + order.getReasonCancel());
            } else {
                txtCancelReason.setVisibility(View.GONE);
            }

        }
    }

    // Method to update orders dynamically
    public void updateOrders(List<Order> orders) {
        this.orderList = orders;
        notifyDataSetChanged();
    }
}
