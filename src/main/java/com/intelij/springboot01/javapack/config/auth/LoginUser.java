package com.intelij.springboot01.javapack.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* target이 생성될 수 있는 위치를 지정
*  parameter로 선언된 객체에서만 사용할 수 있음 */
@Target(ElementType.PARAMETER)
/* 해당 애노테이션이 언제까지 유지할지 알려주는 애노테이션
*  속성은 SOURCE, CLASS, RUNTIME 세 가지이다. */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
