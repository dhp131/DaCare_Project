package com.prm392.dacare.repository;

import com.prm392.dacare.payload.request.AddToCartRequest;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import retrofit2.Callback;

public class CartRepository {
    private APIService apiService;

    public CartRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void addToCart(String id, int quantity, Callback<Void> callback) {

        apiService.addToCart(new AddToCartRequest(id, quantity)).enqueue(callback);
    }
}
