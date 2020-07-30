package com.intelij.springboot01.javapack.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

/* 기본 생성자 자동추가
    public Posts(){} 같은 효과 */
@NoArgsConstructor

public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
