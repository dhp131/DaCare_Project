package com.prm392.dacare.payload.response.OrderResponse;

import com.prm392.dacare.model.Order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersContainer {
        private boolean ok;
        private int status;
        private String message;
        private List<Order> data;

}
