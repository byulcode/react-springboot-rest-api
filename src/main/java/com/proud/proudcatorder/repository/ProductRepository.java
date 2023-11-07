package com.proud.proudcatorder.repository;

import com.proud.proudcatorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
