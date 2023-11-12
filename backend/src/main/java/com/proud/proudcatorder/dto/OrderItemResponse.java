package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.OrderItem;

public record OrderItemResponse(
        String productName, int quantity, long orderPrice
) {
    public static OrderItemResponse from(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
