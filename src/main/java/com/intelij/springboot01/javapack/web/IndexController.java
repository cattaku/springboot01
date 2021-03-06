package com.intelij.springboot01.javapack.web;

import com.intelij.springboot01.javapack.config.auth.LoginUser;
import com.intelij.springboot01.javapack.config.auth.dto.SessionUser;
import com.intelij.springboot01.javapack.service.PostsService;
import com.intelij.springboot01.javapack.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    //리스트
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("list", postsService.findAllDesc());
       /* SessionUser user = (SessionUser) httpSession.getAttribute("user");
       *  @LoginUser 생성으로 반복코드 개선 **/
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
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
