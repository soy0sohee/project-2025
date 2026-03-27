package com.study.ExReviewWebApp.dto;

import com.study.ExReviewWebApp.enumeration.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;
    private UserRole userRole;
}
