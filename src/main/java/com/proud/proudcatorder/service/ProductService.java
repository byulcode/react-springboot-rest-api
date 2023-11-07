package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductResponse;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(CreateProductRequest createProductRequest) {
        Product product = productRepository.save(createProductRequest.toEntity());
        return ProductResponse.from(product);
    }
}
