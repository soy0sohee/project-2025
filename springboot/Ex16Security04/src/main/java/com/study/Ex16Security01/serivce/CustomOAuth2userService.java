package com.study.Ex16Security01.serivce;

import com.study.Ex16Security01.entity.SnsUser;
import com.study.Ex16Security01.entity.SnsUserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service  // @Component - 클래스를 스프링 객체(빈)으로 자동 등록
@RequiredArgsConstructor
public class CustomOAuth2userService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final SnsUserRepository SnsUserRepo;

    @Override  // loadUser() 진입, OAuth 로그인 성공하면 여기서 실행
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2user = delegate.loadUser(userRequest);

        // registrationId : Google, Kakao, Naver 아이디
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2user.getAttributes());

        SnsUser user = saveOrUpdate(attributes);

        // 데이터 수명주기
        // model,request(요청~응답): 리다이렉트하면 데이터 사라짐
        // session(로그인~로그아웃): 아이디, 로그인여부, 필수정보
        // application(웹브라우저종료)
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private SnsUser saveOrUpdate(OAuthAttributes attributes) {
        SnsUser snsUser = SnsUserRepo.findByEmail(attributes.getEmail())
                .map((entity) -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return SnsUserRepo.save(snsUser);
    }


}
