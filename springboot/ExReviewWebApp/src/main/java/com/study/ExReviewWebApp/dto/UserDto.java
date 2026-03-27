package com.study.ExReviewWebApp.dto;

import com.study.ExReviewWebApp.enumeration.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserRole userRole;
}
