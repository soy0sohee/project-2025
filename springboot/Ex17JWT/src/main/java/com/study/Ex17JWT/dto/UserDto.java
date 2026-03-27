package com.study.Ex17JWT.dto;

import com.study.Ex17JWT.enumeration.UserRole;
import lombok.*;

// Response 응답 Dto + Entity 연결 Dto
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
