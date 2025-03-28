package com.prm392.dacare.ui.home.productdetail.addtocart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.prm392.dacare.R;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.viewmodel.ProductDetailViewModel;

public class AddToCartDialog extends BottomSheetDialogFragment {

    private AddToCartDialogViewModel mViewModel;

    private ProductDetailViewModel viewModel;
    private int quantity = 1;

    private Button btnDecrease, btnIncrease, btnAddToCart, btnClose;
    private TextView tvQuantity, tvProductName;
    private ProgressBar progressBar;

    public static AddToCartDialog newInstance() {
        return new AddToCartDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_cart_dialog, container, false);

        init(view);

        return view;
    }

    private void init(View view){
        viewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        btnDecrease = view.findViewById(R.id.btnDecrease);
        btnIncrease = view.findViewById(R.id.btnIncrease);
        btnAddToCart = view.findViewById(R.id.btnAddToCart);

        tvQuantity = view.findViewById(R.id.tvQuantity);
        tvProductName = view.findViewById(R.id.tvProductName);

        progressBar = view.findViewById(R.id.progressBar);

        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            dismiss();
        });

        btnDecrease.setOnClickListener(v -> {
            decreaseQuantity();
        });

        btnIncrease.setOnClickListener(v -> {
           increaseQuantity();
        });

        btnAddToCart.setOnClickListener(v -> {
            Product product = viewModel.getProductLiveData().getValue();
            viewModel.addToCart(product.get_id(), quantity);
            dismiss();
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
                btnAddToCart.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                btnAddToCart.setVisibility(View.VISIBLE);
            }
        });
    }

    private void increaseQuantity(){
        quantity++;
        tvQuantity.setText(String.valueOf(quantity));
    }

    private void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            tvQuantity.setText(String.valueOf(quantity));
        }
    }
}