package com.intelij.springboot01.javapack.domain.user.dto;

import com.intelij.springboot01.javapack.domain.user.Role;
import com.intelij.springboot01.javapack.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email, String picture) {

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }   //of() OAuth2User에서 반환하는 사용자정보는 Map이기 때문에 값을 하나씩 변환해줘야 함.

    private static OAuthAttributes ofGoogle(String userNAmeAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder().name((String) attributes.get("name"))
                                        .email((String) attributes.get("email"))
                                        .picture((String) attributes.get("picture"))
                                        .attributes(attributes)
                                        .nameAttributeKey(userNAmeAttributeName)
                                        .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }  //OAuthAttributes에서 엔티티를 생성하는 시점은 가입하는 할때.
}














