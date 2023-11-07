package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record OrderDetailResponse(
        Long orderId, String orderStatus, long totalPrice, String createdAt, List<OrderItemResponse> orderItems
) {
    public static OrderDetailResponse from(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getOrderStatus().toString(),
                order.calculateTotalPrice(),
                order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                order.getOrderItems().stream()
                        .map(OrderItemResponse::from)
                        .toList()
        );
    }
}
