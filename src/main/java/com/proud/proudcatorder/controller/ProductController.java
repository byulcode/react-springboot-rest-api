package com.proud.proudcatorder.controller;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.service.ImageService;
import com.proud.proudcatorder.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

    @Operation(summary = "상품 등록")
    @PostMapping(value = "/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetailResponse> create(
            @RequestPart(value = "createProductRequest") @Parameter(schema = @Schema(type = "string", format = "binary")) CreateProductRequest createProductRequest,
            @RequestPart(value = "image") MultipartFile image) throws IOException {
        ProductDetailResponse product = productService.create(createProductRequest, image);
        return ResponseEntity.created(URI.create("/products/" + product.id())).body(product);
    }

    @Operation(summary = "상품 카테고리별 조회", description = "카테고리가 입력되지 않으면 전체 상품을 조회한다.")
    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> getProductsByCategory(
            @RequestParam(value = "category", required = false) String category
    ) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @Operation(summary = "상품 아이디로 조회")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> getById(@PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/admin/{productId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "이미지 가져오기")
    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable("imageId") Long imageId) throws Exception {
        byte[] image = imageService.downloadImage(imageId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
