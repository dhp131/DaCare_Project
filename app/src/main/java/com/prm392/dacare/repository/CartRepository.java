package com.prm392.dacare.repository;

import android.util.Log;

import com.prm392.dacare.model.Cart;
import com.prm392.dacare.model.QuizQuestion;
import com.prm392.dacare.payload.request.AddToCartRequest;
import com.prm392.dacare.payload.request.UpdateQuantityRequest;
import com.prm392.dacare.payload.response.AddToCartResponse;
import com.prm392.dacare.payload.response.GetCartResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private final APIService apiService;
    public CartRepository () {
        apiService = APIClient.getClient().create(APIService.class);
    }
    public void getCartInformation(Callback<GetCartResponse> callback) {
        apiService.getCartInformation().enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("CartRepository", "Fetching cart data...");
                    Log.d("CartRepository", "Cart Response: " + response.body().toString());
                    callback.onResponse(call, response);  // Pass the response to the caller
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Log.e("CartRepository", "Failed to fetch cart. Code: " + response.code() + ", Error: " + errorBody);
                    } catch (IOException e) {
                        Log.e("CartRepository", "Error reading response body", e);
                    }
                    callback.onResponse(call, response);  // Still pass the response
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                Log.e("CartRepository", "API request failed: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void AddToCart(AddToCartRequest request, Callback<AddToCartResponse> callback) {
        Log.i("CartRepository", "Adding item to cart...");
        apiService.AddToCart(request).enqueue(callback);
    }

    public void UpdateQuantity (UpdateQuantityRequest request,Callback<Cart> callback ){
        Log.i("CartRepository", "Update item in cart...");
        apiService.UpdateQuantity(request).enqueue(callback);
    }




}
