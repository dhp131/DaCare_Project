package com.prm392.dacare.ui.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.CartAdapter;
import com.prm392.dacare.model.CartItem;
import com.prm392.dacare.viewmodel.CartViewModel;

public class CartFragment extends Fragment {
    private CartViewModel cartViewModel;
    private CartAdapter cartAdapter;
    private TextView textViewTotalPrice;
    private Button buttonOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCart);
        textViewTotalPrice = view.findViewById(R.id.textViewTotalPrice);
        buttonOrder = view.findViewById(R.id.buttonOrder);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("CartFragment", "Fragment created. Fetching cart...");

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartAdapter = new CartAdapter(cartViewModel);
        recyclerView.setAdapter(cartAdapter);

        cartViewModel.getCartLiveData().observe(getViewLifecycleOwner(), cart -> {
            if (cart != null && cart.getProducts() != null) {
                Log.d("CartFragment", "Cart items count: " + cart.getProducts().size());
                for (CartItem item : cart.getProducts()) {
                    Log.d("CartFragment", "Item: " + item.getName() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
                }
                cartAdapter.setCartItems(cart.getProducts());
                // Update total price from Cart object
                double totalPrice = cart.getTotalPrice();
                textViewTotalPrice.setText(String.format("Total: $%.2f", totalPrice));
            } else {
                Log.d("CartFragment", "Cart is null or empty");
                textViewTotalPrice.setText("Total: $0.00");
            }
        });

        buttonOrder.setOnClickListener(v -> {
            Log.d("CartFragment", "Order button clicked");
            cartViewModel.createOrder();
            Toast.makeText(getContext(), "Order placed!", Toast.LENGTH_SHORT).show();
            // Optionally navigate to another screen or clear the UI
        });

        Log.d("CartFragment", "CartFragment Loaded!");
        return view;
    }
    public void refreshCart() {
        if (cartViewModel != null) {
            Log.d("CartFragment", "Refreshing cart...");
            cartViewModel.fetchCartInformation();
        }
    }
}