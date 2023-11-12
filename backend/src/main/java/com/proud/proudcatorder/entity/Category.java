package com.proud.proudcatorder.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    TOY("장난감"),
    SNACK("간식"),
    FEED("사료"),
    SAND("모래"),
    SCRATCHER("스크래쳐")
    ;

    private final String description;

    public static Category of(String category) {
        try {
            return Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 카테고리입니다.");
        }
    }
}
