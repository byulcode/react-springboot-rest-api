package com.proud.proudcatorder.service;

import com.proud.proudcatorder.entity.Image;
import com.proud.proudcatorder.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public byte[] downloadImage(Long productImageId) throws Exception {
        Image image = imageRepository.findById(productImageId)
                .orElseThrow(() -> new NoSuchElementException("이미지를 찾을 수 없습니다."));
        String filePath = image.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
