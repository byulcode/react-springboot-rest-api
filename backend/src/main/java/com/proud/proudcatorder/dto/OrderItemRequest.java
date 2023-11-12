package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.OrderItem;
import com.proud.proudcatorder.entity.Product;

public record OrderItemRequest(
        Long productId, int quantity
) {
    public OrderItem toEntity(Product product) {
        return OrderItem.builder()
                .product(product)
                .quantity(quantity)
                .build();
    }
}
