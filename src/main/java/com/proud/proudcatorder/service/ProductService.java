package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.CreateProductRequest;
import com.proud.proudcatorder.dto.ProductDetailResponse;
import com.proud.proudcatorder.entity.Image;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.infra.util.ImageUtil;
import com.proud.proudcatorder.repository.ImageRepository;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    @Transactional(readOnly = true)
    public byte[] downloadImage(Long productImageId) throws Exception {
        Image image = imageRepository.findById(productImageId)
                .orElseThrow(() -> new NoSuchElementException("이미지를 찾을 수 없습니다."));

        String filePath = image.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
