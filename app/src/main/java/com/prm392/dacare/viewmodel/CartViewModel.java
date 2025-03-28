package com.prm392.dacare.viewmodel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.Cart;
import com.prm392.dacare.model.CartItem;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.payload.response.GetCartResponse;
import com.prm392.dacare.repository.CartRepository;

import java.io.IOException;
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
                    Log.d("CartViewModel", "Fetching cart information...");
                    GetCartResponse cartResponse = response.body();

                    // Log full API response
                    Log.d("CartViewModel", "Full API Response: " + cartResponse.toString());

                    // Convert GetCartResponse to Cart (assuming Cart and GetCartResponse are different)
                    Cart cart = new Cart();
                    cart.setProducts(cartResponse.getData() != null ? cartResponse.getData().getProducts() : new ArrayList<>());
                    cart.setTotalPrice(cartResponse.getTotalPrice() != null ? cartResponse.getTotalPrice() : 0.0);

                    Log.d("CartViewModel", "Cart fetched successfully. Items count: " + cart.getProducts().size());
                    cartLiveData.postValue(cart);
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Log.e("CartViewModel", "Failed to fetch cart. Response code: " + response.code() + ", Error: " + errorBody);
                    } catch (IOException e) {
                        Log.e("CartViewModel", "Error reading response body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                Log.e("CartViewModel", "Error fetching cart: " + t.getMessage());
            }
        });
    }

}
