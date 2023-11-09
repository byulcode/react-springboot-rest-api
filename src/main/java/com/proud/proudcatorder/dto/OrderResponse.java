package com.proud.proudcatorder.dto;


import com.proud.proudcatorder.entity.Order;

import java.time.format.DateTimeFormatter;

public record OrderResponse(
        Long orderId, String orderStatus, String address, String postcode, long totalPrice, String createdAt
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderStatus().toString(),
                order.getAddress(),
                order.getPostcode(),
                order.calculateTotalPrice(),
                order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
