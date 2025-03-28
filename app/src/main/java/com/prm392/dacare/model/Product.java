package com.prm392.dacare.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Parcelable {
    private String _id;
    private String image;
    private String name;
    private String brand;
    private String description;
    private String ingredients;
    private String usage;
    private int price;
    private int productDiscount;
    //private Category category;
    //private List<SkinType> skinTypeId;
    private String usageTime;
    private String origin;
    private String volume;
    private int quantity;
    private String createdAt;
    private String updatedAt;
    private boolean priority;
    private double rating;
    private String expiredDate;
    private boolean active;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }

    public static DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Product product = (Product) obj;
        return product._id.equals(this._id);
    }
}
