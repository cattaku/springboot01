package com.intelij.springboot01.javapack.web;

import com.intelij.springboot01.javapack.service.PostsService;
import com.intelij.springboot01.javapack.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    //리스트
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("list", postsService.findAllDesc());
        return "index";
    }

    //글쓰기
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    //update
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("list", dto);

        return "posts-update";
    }


}
