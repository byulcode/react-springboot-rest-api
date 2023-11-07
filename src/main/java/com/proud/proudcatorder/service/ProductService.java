package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDetailResponse create(CreateProductRequest createProductRequest) {
        Product product = productRepository.save(createProductRequest.toEntity());
        return ProductDetailResponse.from(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDetailResponse> getAll() {
        return productRepository.findAll().stream()
                .map(ProductDetailResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductDetailResponse getById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return ProductDetailResponse.from(product);
    }

    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        productRepository.delete(product);
    }
}
