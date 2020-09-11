package com.intelij.springboot01.javapack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  //jpa Auditing활성화
public class JpaConfig {
}

/* @Configuration 어노테이션은 어노테이션기반 환경구성을 돕는다.
 * 이 어노테이션을 구현함으로써 클래스가 하나 이상의 @Bean 메소드를 제공하고 스프링 컨테이너가 Bean정의를 생성하고
 * 런타임시 그 Bean들이 요청들을 처리할 것을 선언하게 된다.*/