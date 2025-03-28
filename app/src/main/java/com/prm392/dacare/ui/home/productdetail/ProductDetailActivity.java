package com.prm392.dacare.ui.home.productdetail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.ui.home.productdetail.addtocart.AddToCartDialog;
import com.prm392.dacare.viewmodel.ProductDetailViewModel;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ProductDetailViewModel viewModel;
    private ImageView productImage;
    private TextView productName, productBrand, productDescription, productPrice, productRating, productOrigin, productPriceOrigin;
    private TextView productIngredients, productUsage, productVolume, productCategory, productDiscount;
    private TextView productInventory, productUsageTime, productExpiredDate, productCreatedAt;
    private Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_product_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        initViews();

        if (getIntent().hasExtra("product_id")) {
            String productId = getIntent().getStringExtra("product_id");
            // Load product details using the product ID
            viewModel.getProduct(productId);
            viewModel.getProductLiveData().observe(this,data -> {
                loadProductDetails(data);
            });

        } else {
            // Handle the case where the product ID is not provided
            Intent intent = new Intent();
            finish();
        }
    }

    private void initViews() {
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productBrand = findViewById(R.id.productBrand);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        productPriceOrigin = findViewById(R.id.productPriceOrigin);
        productPriceOrigin.setPaintFlags(android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        productRating = findViewById(R.id.productRating);
        productOrigin = findViewById(R.id.productOrigin);
//        productIngredients = findViewById(R.id.productIngredients);
//        productUsage = findViewById(R.id.productUsage);
//        productVolume = findViewById(R.id.productVolume);
//        productCategory = findViewById(R.id.productCategory);
//        productDiscount = findViewById(R.id.productDiscount);
//        productInventory = findViewById(R.id.productInventory);
//        productUsageTime = findViewById(R.id.productUsageTime);
//        productExpiredDate = findViewById(R.id.productExpiredDate);
//        productCreatedAt = findViewById(R.id.productCreatedAt);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        viewModel.getMessage().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void loadProductDetails(Product product) {

        if (product != null) {
            Picasso.get().load(product.getImage()).into(productImage);
            productName.setText(product.getName());
            productBrand.setText(product.getBrand());
            productDescription.setText(product.getDescription());
            if (product.getProductDiscount() >0) {
                int finalPrice = product.getPrice() - (product.getPrice() * product.getProductDiscount() / 100);
                productPrice.setText(String.format(Locale.getDefault(), "%,d VND", finalPrice));
                productPriceOrigin.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
            } else {
                productPrice.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
                productPriceOrigin.setVisibility(View.GONE);
            }
            productRating.setText("Đánh giá: " + Math.round(product.getRating() * 100.0) / 100.0  + "★");
            productOrigin.setText("Xuất xứ: " + product.getOrigin());
//            productIngredients.setText("Ingredients: " + product.getIngredients());
//            productUsage.setText("Usage: " + product.getUsage());
//            productVolume.setText("Volume: " + product.getVolume() + "ml");
//            productCategory.setText("Category: " + product.getCategory().getName());
//            productDiscount.setText("Discount: " + product.getProductDiscount() + "%");
//            productInventory.setText("Inventory: " + product.getInventory());
//            productUsageTime.setText("Usage Time: " + product.getUsageTime());
//            productExpiredDate.setText("Expires: " + product.getExpiredDate());
//            productCreatedAt.setText("Created: " + product.getCreatedAt());

            btnBuyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddToCartDialog bottomSheet = new AddToCartDialog();
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                }
            });
        }
    }
}