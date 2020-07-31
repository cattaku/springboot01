package com.intelij.springboot01.javapack.service;

import com.intelij.springboot01.javapack.domain.posts.Posts;
import com.intelij.springboot01.javapack.domain.posts.PostsRepository;
import com.intelij.springboot01.javapack.web.dto.PostsListResponseDto;
import com.intelij.springboot01.javapack.web.dto.PostsResponseDto;
import com.intelij.springboot01.javapack.web.dto.PostsSaveRequestDto;
import com.intelij.springboot01.javapack.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/*final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복으로 생성*/
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 게시글이 없습니다. id =" + id ));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {

        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
