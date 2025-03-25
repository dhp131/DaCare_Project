package com.prm392.dacare.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.payload.response.LoginResponse;
import com.prm392.dacare.repository.ProductRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<Product> productLiveData = new MutableLiveData<>();

    public ProductDetailViewModel() {
        productRepository = new ProductRepository();
    }
    public void getProduct(String id){
        productRepository.getProductById(id, new Callback<>(){
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productLiveData.setValue(response.body());
                } else {
                    productLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                productLiveData.setValue(null);
            }
        });

    }

    public LiveData<Product> getProductLiveData() {
        return productLiveData;
    }
}
