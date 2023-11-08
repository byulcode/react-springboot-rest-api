package com.proud.proudcatorder.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private long price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Builder
    public Product(String name, String description, long price, Category category, Long imageId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageId = imageId;
    }

    public void addImageId(Long imageId) {
        this.imageId = imageId;
    }
}
