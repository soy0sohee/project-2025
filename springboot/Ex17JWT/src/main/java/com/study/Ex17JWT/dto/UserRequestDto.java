package com.study.Ex17JWT.dto;

import com.study.Ex17JWT.enumeration.UserRole;
import lombok.*;

// signup, login 데이터 바인딩
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String email;
    private String password;
    private UserRole userRole;
}
