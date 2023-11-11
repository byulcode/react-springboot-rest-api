package com.proud.proudcatorder.repository;

import com.proud.proudcatorder.entity.Category;
import com.proud.proudcatorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE (:category IS NULL OR p.category = :category) ORDER BY p.createdAt DESC")
    List<Product> findByCategory(@Param("category") Category category);
}
