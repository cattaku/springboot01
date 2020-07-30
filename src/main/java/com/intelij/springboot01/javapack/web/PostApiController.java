package com.intelij.springboot01.javapack.web;

import com.intelij.springboot01.javapack.service.PostsService;
import com.intelij.springboot01.javapack.web.dto.PostsResponseDto;
import com.intelij.springboot01.javapack.web.dto.PostsSaveRequestDto;
import com.intelij.springboot01.javapack.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/*
@RestController
컨트롤러를 JSON을 반환하는 형태로 만들어줌.
@Responsebody를 한번에 사용할 수 있게 해주는 방식
*/
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;

    //등록
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
