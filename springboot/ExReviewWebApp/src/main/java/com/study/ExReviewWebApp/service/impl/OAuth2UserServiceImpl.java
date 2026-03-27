package com.study.ExReviewWebApp.service.impl;

import com.study.ExReviewWebApp.entity.SnsUsers;
import com.study.ExReviewWebApp.repository.SnsUsersRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

//사용자가 로그인을 누르면 OAuth2 인증을 처리 -> 성공하면 자동으로 loadUser() 호출
@Service
@RequiredArgsConstructor
//OAuth2UserService : loadUser() 메서드를 반드시 구현해야 함
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final SnsUsersRepository snsUsersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2UserService delegate = new DefaultOAuth2UserService(); //DefaultOAuth2UserService에게 위임
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //어떤 소셜인지 식별
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //google, kakao, naver 소셜 종류
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); //사용자를 식별하는 PK필드

        //소셜별 응답을 공통 구조로 변환 (필드명 통일)
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        //DB 저장 or 업데이트
        SnsUsers user = saveOrUpdate(attributes);

        //세션 저장
        httpSession.setAttribute("user", new SessionUser(user));

        //인증 완료 반환
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), //권한 정보
                attributes.getAttributes(), //원본 사용자 정보
                attributes.getNameAttributeKey() //PK 필드 이름
        );
    }
    private SnsUsers saveOrUpdate(OAuthAttributes attributes) {
        SnsUsers user = snsUsersRepository.findByEmail(attributes.getEmail()) //이메일로 조회
                .map((entity) -> entity.update(attributes.getName(), attributes.getPicture())) //있으면 최신값으로 업데이트
                .orElse(attributes.toEntity()); //없으면 새 SnsUsers 생성
        return snsUsersRepository.save(user);
    }
}
