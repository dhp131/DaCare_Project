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

    protected Product(Parcel in) {
        _id = in.readString();
        image = in.readString();
        name = in.readString();
        brand = in.readString();
        description = in.readString();
        ingredients = in.readString();
        usage = in.readString();
        price = in.readInt();
        productDiscount = in.readInt();
        usageTime = in.readString();
        origin = in.readString();
        volume = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        priority = in.readByte() != 0;
        rating = in.readDouble();
        expiredDate = in.readString();
        active = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(brand);
        dest.writeString(description);
        dest.writeString(ingredients);
        dest.writeString(usage);
        dest.writeInt(price);
        dest.writeInt(productDiscount);
        dest.writeString(usageTime);
        dest.writeString(origin);
        dest.writeString(volume);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeByte((byte) (priority ? 1 : 0));
        dest.writeDouble(rating);
        dest.writeString(expiredDate);
        dest.writeByte((byte) (active ? 1 : 0));
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
