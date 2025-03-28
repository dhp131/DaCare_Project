package com.prm392.dacare.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Product;
import com.prm392.dacare.ui.home.productdetail.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Objects;

public class ProductPaginationAdapter extends PagedListAdapter<Product, ProductPaginationAdapter.ProductViewHolder> {

    public ProductPaginationAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        if (product != null) {
            holder.bind(product);
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPrice,txtBrand, txtOriginPrice;
        private ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtOriginPrice = itemView.findViewById(R.id.txtOriginPrice);
            txtOriginPrice.setPaintFlags(android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Product product) {
            txtName.setText(product.getName());
            txtBrand.setText(product.getBrand());
            if (product.getProductDiscount() >0) {
                int finalPrice = product.getPrice() - (product.getPrice() * product.getProductDiscount() / 100);
                txtPrice.setText(String.format(Locale.getDefault(), "%,d VND", finalPrice));
                txtOriginPrice.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
            } else {
                txtPrice.setText(String.format(Locale.getDefault(), "%,d VND", product.getPrice()));
                txtOriginPrice.setVisibility(View.GONE);
            }

            Picasso.get().load(product.getImage()).into(imgProduct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("product_id", product.get_id() );
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.get_id().equals(newItem.get_id());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.equals(newItem);
                }
            };
}