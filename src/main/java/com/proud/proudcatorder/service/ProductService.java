package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductResponse;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(CreateProductRequest createProductRequest) {
        Product product = productRepository.save(createProductRequest.toEntity());
        return ProductResponse.from(product);
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse getById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return ProductResponse.from(product);
    }
}
