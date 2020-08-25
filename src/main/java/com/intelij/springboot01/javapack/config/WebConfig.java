package com.intelij.springboot01.javapack.config;

import com.intelij.springboot01.javapack.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);

    }
}

/* HandlerMethodArgumentResolver는 항상 WebMvcConfigurer 의 addArgumentResolvers()를 통해 추가해야 함
*  다른 Handler-MethodAregumentResolver가 필요하다면 같은 방식으로 추가할 것 */