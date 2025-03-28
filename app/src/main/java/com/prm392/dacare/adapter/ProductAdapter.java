package com.prm392.dacare.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.ui.home.productdetail.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.txtName.setText(product.getName());
        holder.txtBrand.setText(product.getBrand());
        if (product.getProductDiscount() >0) {
            int finalPrice = product.getPrice() - (product.getPrice() * product.getProductDiscount() / 100);
            holder.txtPrice.setText(String.format(Locale.getDefault(), "%,d VND", finalPrice));
            holder.txtOriginPrice.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
        } else {
            holder.txtPrice.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
            holder.txtOriginPrice.setVisibility(View.GONE);
        }
        Picasso.get().load(product.getImage()).into(holder.imgProduct);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
            intent.putExtra("product_id", product.get_id());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtBrand, txtOriginPrice;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtOriginPrice = itemView.findViewById(R.id.txtOriginPrice);
            txtOriginPrice.setPaintFlags(android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
