package com.prm392.dacare.repository.pagingsource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.payload.response.PaginationResponse;
import com.prm392.dacare.repository.ProductRepository;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPagingSource extends PageKeyedDataSource<Integer, Product> {
    private final ProductRepository repository;
    private static final String TAG = ProductPagingSource.class.getSimpleName();

    public ProductPagingSource() {
        this.repository = new ProductRepository();
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, Product> loadCallback) {
        repository.getProducts(loadParams.requestedLoadSize,loadParams.key, new Callback<PaginationResponse<Product>>(){
            @Override
            public void onResponse(Call<PaginationResponse<Product>> call, Response<PaginationResponse<Product>> response) {
                Integer nextKey = (loadParams.key == response.body().getTotalPages()) ? null : loadParams.key+1;
                if(response.isSuccessful() && response.body() != null) {
                    loadCallback.onResult(response.body().getData(), nextKey);
                } else {
                    loadCallback.onResult(new ArrayList<Product>(),null);
                }
            }

            @Override
            public void onFailure(Call<PaginationResponse<Product>> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, Product> loadInitialCallback) {
        repository.getProducts(loadInitialParams.requestedLoadSize,1, new Callback<PaginationResponse<Product>>(){

            @Override
            public void onResponse(Call<PaginationResponse<Product>> call, Response<PaginationResponse<Product>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    loadInitialCallback.onResult(response.body().getData(), null, 2);

                } else {
                    loadInitialCallback.onResult(new ArrayList<Product>(),null,null);
                }
            }

            @Override
            public void onFailure(Call<PaginationResponse<Product>> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, Product> loadCallback) {

    }
}
