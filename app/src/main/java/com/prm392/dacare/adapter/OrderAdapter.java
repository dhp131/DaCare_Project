package com.prm392.dacare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Order;

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
        // Inflate the item_order.xml layout for each order
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.bind(order);
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
            txtOrderId.setText("Order #" + order.getId());

            // Format and set the order date
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            txtOrderDate.setText(sdf.format(order.getOrderDate()));

            // Set customer name (assuming getCustomer() returns a User object with getName())
            txtCustomer.setText(order.getCustomer().getName());

            // Set order status
            txtStatus.setText(order.getStatus());

            // Format and set the order amount (assuming amount is an integer)
            txtAmount.setText(String.format(Locale.getDefault(), "%,d VND", order.getAmount()));

            // Set payment status
            txtPaymentStatus.setText(order.isPaid() ? "Paid" : "Unpaid");

            // Display cancel reason if order is cancelled and a reason exists
            if (order.getStatus().equalsIgnoreCase("Cancelled") && order.getReasonCancel() != null) {
                txtCancelReason.setVisibility(View.VISIBLE);
                txtCancelReason.setText("Reason: " + order.getReasonCancel());
            } else {
                txtCancelReason.setVisibility(View.GONE);
            }

            // If you have a URL or resource for customer avatar, load it here (e.g., with Picasso)
            // Example: Picasso.get().load(order.getCustomer().getAvatarUrl()).into(imgCustomer);
        }
    }
}
