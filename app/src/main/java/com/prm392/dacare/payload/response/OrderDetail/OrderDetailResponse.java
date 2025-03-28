package com.prm392.dacare.payload.response.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private boolean ok;
    private int status;
    private String message;
    private OrderContainer order;
}
