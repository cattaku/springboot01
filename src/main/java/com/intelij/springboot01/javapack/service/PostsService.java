package com.intelij.springboot01.javapack.service;

import com.intelij.springboot01.javapack.domain.posts.Posts;
import com.intelij.springboot01.javapack.domain.posts.PostsRepository;
import com.intelij.springboot01.javapack.web.dto.PostsResponseDto;
import com.intelij.springboot01.javapack.web.dto.PostsSaveRequestDto;
import com.intelij.springboot01.javapack.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복으로 생성*/
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 게시글이 없습니다. id =" + id ));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return  id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 게시글이 없습니다. id =" + id ));
        return new PostsResponseDto(entity);
    }

}
