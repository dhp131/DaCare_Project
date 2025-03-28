package com.prm392.dacare.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.repository.CartRepository;
import com.prm392.dacare.repository.ProductRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {
    private final ProductRepository productRepository;

    private final CartRepository cartRepository;
    private final MutableLiveData<Product> productLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    private final MutableLiveData<String> message = new MutableLiveData<>();

    public ProductDetailViewModel() {
        productRepository = new ProductRepository();
        cartRepository = new CartRepository();
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
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getMessage() {
        return message;
    }


    public void addToCart(String id, int quantity) {
        isLoading.setValue(true);
        cartRepository.addToCart(id, quantity, new Callback<>(){

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    message.setValue("Thêm vào giỏ hàng thành công");
                } else {
                    message.setValue("Thêm vào giỏ hàng thất bại");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                isLoading.setValue(false);
                message.setValue("Thêm vào giỏ hàng thất bại");
            }
        });
    }
}
