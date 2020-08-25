package com.intelij.springboot01.javapack.config.auth.dto;

import com.intelij.springboot01.javapack.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name    = user.getName();
        this.email   = user.getEmail();
        this.picture = user.getPicture();


    }

}
/* Serializable
 *  자바 시스템 내부에서 사용되는 Object 또는 Data를
 *  외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환 */
