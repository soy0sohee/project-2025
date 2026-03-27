package com.study.ExReviewWebApp.service.impl;

import com.study.ExReviewWebApp.entity.SnsUsers;
import lombok.Getter;

import java.io.Serializable;

//로그인된 사용자 정보를 세션에 안전하게 저장하기 위한 전용 객체
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(SnsUsers user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
