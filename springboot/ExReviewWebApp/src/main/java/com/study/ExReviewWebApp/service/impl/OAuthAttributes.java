package com.study.ExReviewWebApp.service.impl;

import com.study.ExReviewWebApp.entity.SnsUsers;
import com.study.ExReviewWebApp.enumeration.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

//소셜마다 다른 응답을 공통구조로 변환
@Getter
public class OAuthAttributes {
    //클래스 선언
    private Map<String, Object> attributes; //원본 응답 전체
    private String nameAttributeKey; //PK 필드 이름
    private String name; //사용자 이름
    private String email; //이메일
    private String picture; //프로필 사진

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //of(): 어떤 소셜인지 판단하고 각 메서드로 분기
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        //kakao,naver = PK 필드 "id"로 고정(하드코딩)
//        if ("kakao".equals(registrationId)) {
//            return ofKakao("id", attributes);
//        }
//        if ("naver".equals(registrationId)) {
//            return ofNaver("id", attributes);
//        }
        //google = PK 필드 값 그대로 사용
        return ofGoogle(userNameAttributeName, attributes);
    }

    //Google파싱
    //OAuthAttributes에 응답을 빌드하였다가 바로 꺼낼 수 있음
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //DB저장용 엔티티 변환 - 신규회원 저장
    public SnsUsers toEntity() {
        return SnsUsers.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .userRole(UserRole.USER)
                .build();
    }
}
