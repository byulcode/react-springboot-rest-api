package com.proud.proudcatorder.controller;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetailResponse> create(
            @RequestPart(value = "createProductRequest") @Parameter(schema = @Schema(type = "string", format = "binary")) CreateProductRequest createProductRequest,
            @RequestPart(value = "image") MultipartFile image) throws IOException {
        ProductDetailResponse product = productService.create(createProductRequest, image);
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

    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable("imageId") Long imageId) throws Exception {
        byte[] image = productService.downloadImage(imageId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
