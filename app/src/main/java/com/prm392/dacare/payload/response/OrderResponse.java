package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.model.User;

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
public class OrderResponse {
    private String id;
    private User customer;
    private int amount;
    private String status;
    private List<Product> products;
    private Date orderDate;
    private String reasonCancel;
    private boolean isPaid;
}
