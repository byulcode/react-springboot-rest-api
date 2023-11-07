package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.Product;

import java.time.format.DateTimeFormatter;

public record ProductDetailResponse(
        Long id, String name, String description, long price, String category, String createdAt
) {
    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getDescription(),
                product.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
