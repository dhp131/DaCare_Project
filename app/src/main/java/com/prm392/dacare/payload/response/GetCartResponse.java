package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.Cart;
import com.prm392.dacare.model.CartItem;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCartResponse {
    private Integer status;
    private String ok;
    private Cart data;
    private Double totalPrice;

}
