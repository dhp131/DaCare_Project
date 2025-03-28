package com.prm392.dacare.payload.response.OrderDetail;

import com.prm392.dacare.model.Order;
import com.prm392.dacare.model.OrderExtend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderContainer {
    private OrderExtend order;
}
