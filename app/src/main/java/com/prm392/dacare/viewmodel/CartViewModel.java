package com.prm392.dacare.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.Cart;
import com.prm392.dacare.model.CartItem;
import com.prm392.dacare.payload.request.CreateOrderRequest;

import com.prm392.dacare.model.OrderItem;
import com.prm392.dacare.payload.request.UpdateQuantityRequest;
import com.prm392.dacare.payload.response.GetCartResponse;
import com.prm392.dacare.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    private final CartRepository cartRepository;
    private final MutableLiveData<Cart> cartLiveData = new MutableLiveData<>();

    public CartViewModel() {
        Log.d("CartViewModel", "ViewModel initialized");
        cartRepository = new CartRepository();
        fetchCartInformation();
    }

    public LiveData<Cart> getCartLiveData() {
        return cartLiveData;
    }

    public void fetchCartInformation() {
        cartRepository.getCartInformation(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GetCartResponse cartResponse = response.body();
                    Cart cart = new Cart();
                    cart.setProducts(cartResponse.getData() != null ? cartResponse.getData().getProducts() : new ArrayList<>());
                    Log.e("CartViewModel", "TotalPrice " + cartResponse.getData().getTotalPrice());
                    cart.setTotalPrice(cartResponse.getData().getTotalPrice());
                    Log.e("CartViewModel", "TotalPriceCart " + cart.getTotalPrice());
                    cartLiveData.setValue(cart);
                } else {
                    Log.e("CartViewModel", "Failed to fetch cart: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                Log.e("CartViewModel", "Error fetching cart: " + t.getMessage());
            }
        });
    }

    // New method to update quantity and refresh cart
    public void updateCartQuantity(String productId, int newQuantity) {
        UpdateQuantityRequest request = new UpdateQuantityRequest(productId, newQuantity);
        cartRepository.UpdateQuantity(request, new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    // Fetch updated cart after successful update
                    fetchCartInformation();
                } else {
                    Log.e("CartViewModel", "Failed to update quantity: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("CartViewModel", "Error updating quantity: " + t.getMessage());
            }
        });
    }
    public void createOrder() {
        Cart currentCart = cartLiveData.getValue();
        if (currentCart == null || currentCart.getProducts() == null || currentCart.getProducts().isEmpty()) {
            Log.d("CartViewModel", "Cart is empty, cannot create order");
            return;
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : currentCart.getProducts()) {
            orderItems.add(new OrderItem(item.getProductId(), item.getQuantity()));
        }

        CreateOrderRequest request = new CreateOrderRequest(orderItems);
        cartRepository.CreateOrder(request, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("CartViewModel", "Order created successfully");
                    // Optionally clear the cart or fetch updated cart
                    fetchCartInformation(); // This will refresh the cart (might clear it if API does so)
                } else {
                    Log.e("CartViewModel", "Failed to create order: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CartViewModel", "Error creating order: " + t.getMessage());
            }
        });
    }
}