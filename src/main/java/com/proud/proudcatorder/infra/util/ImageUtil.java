package com.proud.proudcatorder.infra.util;

import com.proud.proudcatorder.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ImageUtil {

    private static final String baseFolderPath = "C:\\proudcat\\";

    private static final List<String> allowedImageExtensions = Arrays.asList(".jpg", ".jpeg", ".png", ".gif"); // 허용할 이미지 확장자 목록

    public static Image parseImageFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new NoSuchFileException("이미지를 등록해주세요");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);

        if (fileExtension == null || !allowedImageExtensions.contains(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다.");
        }

        String currentDate = getCurrentDate();
        String uniqueFileName = generateUniqueFileName(fileExtension);

        String folderPath = Paths.get(baseFolderPath, "image", currentDate).toString();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        Path filePath = Paths.get(folderPath, uniqueFileName);

        File file = filePath.toFile();
        multipartFile.transferTo(file);

        return Image.builder()
                .origFileName(originalFilename)
                .type(fileExtension)
                .filePath(filePath.toString())
                .fileSize(multipartFile.getSize())
                .build();
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return null; // 파일 확장자가 없음
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private static String getCurrentDate() {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private static String generateUniqueFileName(String fileExtension) {
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "");
        return uniqueFileName + fileExtension;
    }
}
