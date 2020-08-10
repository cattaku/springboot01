package com.intelij.springboot01.javapack.config.auth;

import com.intelij.springboot01.javapack.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 사용을 위한 옵션들을 disable
                .and()
                .authorizeRequests() //URL별 권한 관리 설정 옵션 antMatchers옵션 사용을 위한
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())   //권한 관리 대상 지정 옵션, url/http별로 관리 가능
                .anyRequest().authenticated()  //설정값 외의 나머지 url을 나타냄, authenticated()을 추가하여 나머지 url들은 모두 인증된 사용자들에게만 허용하게 함
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()  //oauth2 로그인 성구 후 사용자 정보를 가져올 때의 설정 담당
                .userService(customOAuth2UserService);  //로그인 성공 시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록함

    }
}