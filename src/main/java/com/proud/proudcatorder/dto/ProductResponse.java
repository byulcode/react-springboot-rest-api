package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.Product;

import java.time.format.DateTimeFormatter;

public record ProductResponse(
        Long id, String name, String description, long price, String category, String createdAt
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().toString(),
                product.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
