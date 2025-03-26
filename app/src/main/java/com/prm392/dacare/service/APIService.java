package com.prm392.dacare.service;

import com.prm392.dacare.model.Order;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.model.User;
import com.prm392.dacare.payload.request.LoginRequest;
import com.prm392.dacare.payload.response.LoginResponse;
import com.prm392.dacare.payload.response.OrderResponse;
import com.prm392.dacare.payload.response.PaginationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //Auth
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    //Product
    @GET("product")
    Call<PaginationResponse<Product>> getProducts(@Query("pageSize") int pageSize, @Query("page") int page);

    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("order")
    Call<OrderResponse> getOrder();
}
