package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.Category;
import com.proud.proudcatorder.entity.Product;

public record CreateProductRequest(
        String name, String description, long price, String category
) {
    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(Category.of(category.toUpperCase()))
                .build();
    }
}
