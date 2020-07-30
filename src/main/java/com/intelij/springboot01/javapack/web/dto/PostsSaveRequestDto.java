package com.intelij.springboot01.javapack.web.dto;

import com.intelij.springboot01.javapack.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

/*
 * 많은 서비스클래스나 비지니스 로직들이 Entity클래스를 기준으로 동작하기 때문에
 * Entity 클래스가 변경되면 여러 클래스에 영향을 끼치므로
 * Entity 클래스와 Controller에서 사용할 DTO는 반드시 분리해서 사용
*/