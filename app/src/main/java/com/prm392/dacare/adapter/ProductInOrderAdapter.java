package com.prm392.dacare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Product;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductInOrderAdapter extends RecyclerView.Adapter<ProductInOrderAdapter.ProductViewHolder> {

    private List<Product> productList;

    // Constructor
    public ProductInOrderAdapter(List<Product> productList) {
        this.productList = productList;
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductQuantity;
        TextView tvProductDiscount;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
            tvProductDiscount = itemView.findViewById(R.id.tvProductDiscount);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_in_order, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Set product name
        holder.tvProductName.setText(product.getName());

        // Set product price (formatting VND)
        String priceText = "Price: " + String.format("%,d", product.getPrice()) + " VND";
        holder.tvProductPrice.setText(priceText);

        // Hide quantity and discount since they're not in Product class
        holder.tvProductQuantity.setVisibility(View.GONE);
        holder.tvProductDiscount.setVisibility(View.GONE);

        // Load product image using Picasso
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.drawable.ic_dashboard_black_24dp) // Default placeholder
                    .error(R.drawable.ic_dashboard_black_24dp)      // Fallback image if loading fails
                    .resize(80, 80)                                 // Resize to match ImageView dimensions
                    .centerCrop()                                   // Crop to fit ImageView
                    .into(holder.ivProductImage);
        } else {
            // Set default image if no image URL is provided
            holder.ivProductImage.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    // Method to update data
    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
}