package com.prm392.dacare.repository;

import android.util.Log;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.payload.request.AddToCartRequest;
import com.prm392.dacare.payload.response.PaginationResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import retrofit2.Callback;

public class ProductRepository {
    private APIService apiService;

    public ProductRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void getProducts(int pageSize, int page, Callback<PaginationResponse<Product>> callback) {
        Log.i("ProductRepository: ", "ProductRepository: getProducts: "+pageSize+" "+page);
        apiService.getProducts(pageSize, page).enqueue(callback);
    }



    public void getProductById(String id, Callback<Product> callback) {
        apiService.getProductById(id).enqueue(callback);
    }
}
