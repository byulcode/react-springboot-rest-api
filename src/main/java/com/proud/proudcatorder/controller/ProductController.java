package com.proud.proudcatorder.controller;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDetailResponse> create(@RequestBody CreateProductRequest createProductRequest) {
        ProductDetailResponse product = productService.create(createProductRequest);
        return ResponseEntity.created(URI.create("/products/" + product.id())).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> getById(@PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
