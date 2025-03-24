package com.prm392.dacare.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String _id;
    private String image;
    private String name;
    private String brand;
    private String description;
    private String ingredients;
    private String usage;
    private int price;
    private int productDiscount;
    private String category;
    private List<String> skinTypeId;
    private String usageTime;
    private String origin;
    private String volume;
    private int inventory;
    private String createdAt;
    private String updatedAt;
    private boolean priority;
    private double rating;
    private String expiredDate;
    private boolean active;
}
