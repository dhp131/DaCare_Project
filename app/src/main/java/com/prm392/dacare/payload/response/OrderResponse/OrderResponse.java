package com.prm392.dacare.payload.response.OrderResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
        private boolean ok;
        private int status;
        private String message;
        private OrdersContainer orders;
}

