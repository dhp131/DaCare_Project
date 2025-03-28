package com.prm392.dacare.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String _id;
    private String customerId;
    private int amount;
    private String status;
    private List<Product> products;
    private Date orderDate;
    private String reasonCancel;
    private boolean isPaid;
}
