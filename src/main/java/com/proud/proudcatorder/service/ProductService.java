package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.entity.Category;
import com.proud.proudcatorder.entity.Image;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.infra.util.ImageUtil;
import com.proud.proudcatorder.repository.ImageRepository;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public ProductDetailResponse create(CreateProductRequest createProductRequest, MultipartFile image) throws IOException {
        Product product = createProductRequest.toEntity();
        Image productImage = imageRepository.save(ImageUtil.parseImageFile(image));
        product.addImageId(productImage.getId());
        product = productRepository.save(product);
        return ProductDetailResponse.from(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDetailResponse> getProductsByCategory(String category) {
        Category selectedCategory = StringUtils.hasText(category) ? Category.valueOf(category.toUpperCase()) : null;
        return productRepository.findByCategory(selectedCategory).stream()
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
