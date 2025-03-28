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
public class OrderExtend {
    private String _id;
    private User customerId;
    private int amount;
    private String status;
    private List<Product> products;
    private Date orderDate;
    private String reasonCancel;
    private boolean isPaid;
}
