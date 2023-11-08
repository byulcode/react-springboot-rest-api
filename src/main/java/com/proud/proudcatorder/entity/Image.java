package com.proud.proudcatorder.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origFileName;
    private String type;
    private String filePath;
    private Long fileSize;

    @Builder
    public Image(String origFileName, String type, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.type = type;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
