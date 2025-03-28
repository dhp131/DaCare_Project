package com.prm392.dacare.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392.dacare.R;
import com.prm392.dacare.model.CartItem;
import com.prm392.dacare.payload.request.UpdateQuantityRequest;
import com.prm392.dacare.repository.CartRepository;
import com.prm392.dacare.viewmodel.CartViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private Context context;
    private CartViewModel cartViewModel;

    public CartAdapter(CartViewModel cartViewModel) {
        this.cartViewModel = cartViewModel;
    }

    public void setCartItems(List<CartItem> items) {
        this.cartItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText("$" + item.getPrice());
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

        Glide.with(holder.imageView.getContext()).load(item.getImage()).into(holder.imageView);

        holder.plusButton.setOnClickListener(v -> {
            int newQuantity = item.getQuantity() + 1;
            updateQuantity(item, newQuantity, holder.quantityTextView);
        });

        holder.minusButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                int newQuantity = item.getQuantity() - 1;
                updateQuantity(item, newQuantity, holder.quantityTextView);
            }
        });
    }

    private void updateQuantity(CartItem item, int newQuantity, TextView quantityTextView) {
        // Store original quantity for potential revert
        int originalQuantity = item.getQuantity();

        // Update UI optimistically
        quantityTextView.setText(String.valueOf(newQuantity));

        // Call ViewModel to update quantity and fetch updated cart
        cartViewModel.updateCartQuantity(item.getProductId(), newQuantity);

        // Note: We don't need to handle the response here anymore
        // The ViewModel will update the LiveData, which the Fragment observes
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, priceTextView, quantityTextView;
        Button plusButton, minusButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCartItem);
            nameTextView = itemView.findViewById(R.id.textViewCartItemName);
            priceTextView = itemView.findViewById(R.id.textViewCartItemPrice);
            quantityTextView = itemView.findViewById(R.id.textViewCartItemQuantity);
            plusButton = itemView.findViewById(R.id.buttonPlus);
            minusButton = itemView.findViewById(R.id.buttonMinus);
        }
    }
}