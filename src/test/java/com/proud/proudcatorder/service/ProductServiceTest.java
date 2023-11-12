package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.entity.Category;
import com.proud.proudcatorder.repository.ImageRepository;
import com.proud.proudcatorder.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void create() throws IOException {
        CreateProductRequest createProductRequest = new CreateProductRequest("상품1", "상품설명", 2000, "TOY");
        MultipartFile mockImage = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());

        ProductDetailResponse result = productService.create(createProductRequest, mockImage);

        assertThat(result.name()).isEqualTo(createProductRequest.name());
    }

    @Test
    void getProductsByCategory() throws IOException {
        CreateProductRequest createProductRequest1 = new CreateProductRequest("상품1", "상품설명", 2000, "TOY");
        CreateProductRequest createProductRequest2 = new CreateProductRequest("상품2", "상품설명", 2000, "SNACK");
        MultipartFile mockImage = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        productService.create(createProductRequest1, mockImage);
        productService.create(createProductRequest2, mockImage);

        List<ProductDetailResponse> result = productService.getProductsByCategory("SNACK");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).category()).isEqualTo(Category.SNACK.getDescription());
    }

    @Test
    void getById() {

    }

    @Test
    void delete() {
    }

    @AfterEach
    void clear() {
        productRepository.deleteAll();
    }
}
