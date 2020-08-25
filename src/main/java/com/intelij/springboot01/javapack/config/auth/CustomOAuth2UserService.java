package com.intelij.springboot01.javapack.config.auth;

import com.intelij.springboot01.javapack.config.auth.dto.OAuthAttributes;
import com.intelij.springboot01.javapack.config.auth.dto.SessionUser;
import com.intelij.springboot01.javapack.domain.user.User;
import com.intelij.springboot01.javapack.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /*  registrationId
         *  현재 로그인중인 서비스를 구분하는 코드 */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*  userNameAttributeName
         *  OAuth2 로그인시 키가 되는 필드값
         *  구글 기본 코드는 sub, 네이버/카카오는 기본코드를 지원하지 않음 */
        String userNameAttributeName = userRequest.getClientRegistration()
                                                  .getProviderDetails()
                                                  .getUserInfoEndpoint()
                                                  .getUserNameAttributeName();

        /*  OAuthAttributes
         *  OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스 */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());


        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); //

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                     attributes.getAttributes(),
                                     attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(
                                                                                        attributes.getName(),
                                                                                        attributes.getPicture()))
                                                                      .orElse(attributes.toEntity());

        return userRepository.save(user);

    }
}
