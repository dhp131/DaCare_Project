package com.prm392.dacare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.prm392.dacare.R;
import com.prm392.dacare.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;

    public void setCartItems(List<CartItem> items) {
        this.cartItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
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
            item.setQuantity(item.getQuantity() + 1);
            holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
        });

        holder.minusButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
            }
        });
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