package com.prm392.dacare.service;

import com.prm392.dacare.model.Cart;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.model.QuizQuestion;
import com.prm392.dacare.model.User;
import com.prm392.dacare.payload.request.AddToCartRequest;
import com.prm392.dacare.model.Routine;
import com.prm392.dacare.payload.request.RoutineRequest;
import com.prm392.dacare.payload.response.RoutineResponse;
import com.prm392.dacare.payload.request.CreateOrderRequest;
import com.prm392.dacare.payload.request.LoginRequest;
import com.prm392.dacare.payload.request.QuizResultRequest;
import com.prm392.dacare.payload.request.UpdateQuantityRequest;
import com.prm392.dacare.payload.response.AddToCartResponse;
import com.prm392.dacare.payload.response.GetCartResponse;
import com.prm392.dacare.payload.response.LoginResponse;
import com.prm392.dacare.payload.response.OrderDetail.OrderDetailResponse;
import com.prm392.dacare.payload.response.OrderResponse.OrderResponse;
import com.prm392.dacare.payload.response.PaginationResponse;
import com.prm392.dacare.payload.response.UserResponse;
import com.prm392.dacare.payload.response.QuizResultResponse;
import com.prm392.dacare.payload.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //Auth
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);


    @GET("auth/getUserById")
    Call<UserResponse> getUser();

    //Product
    @GET("product")
    Call<PaginationResponse<Product>> getProducts(@Query("pageSize") int pageSize, @Query("page") int page);

    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("quiz-question")
    Call<List<QuizQuestion>> getQuizQuestions();
    @GET("order/getOrderByStatus")
    Call<OrderResponse> getOrder();

    @POST("skin-types/skin-analysis")
    Call<QuizResultResponse> skinAnalysis(@Body QuizResultRequest request);

    @GET("routine/getRoutineBySkinType/{skinTypeId}")
    Call<Routine> getRoutineBySkinType(@Path("skinTypeId") String skinTypeId);



    //Cart
    @POST("cart/addToCart")
    Call<Void> addToCart(@Body AddToCartRequest addToCartRequest);
    @GET("order/getOrderById/{id}")
    Call<OrderDetailResponse> getOrderById(@Path("id") String id);

    //Cart
    @GET("cart/getCartInfor/")
    Call<GetCartResponse> getCartInformation();
    @POST("cart/addToCart")
    Call<AddToCartResponse> AddToCart(@Body AddToCartRequest request);
    @PUT("cart/updateQuantity")
    Call<Cart> UpdateQuantity(@Body UpdateQuantityRequest request);

    //Order
    @POST("order/createOrder")
    Call<Void> CreateOrder (@Body CreateOrderRequest request);
}
