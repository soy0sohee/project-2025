package com.study.Ex16Security01.dto;

import com.study.Ex16Security01.entity.MemberEntity;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateDto {
    private Long id;
    @Size(min = 4, message = "아이디는 4자 이상이어야 합니다.")
    private String username;
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    @Pattern(regexp = ".*[a-z].*", message = "비밀번호에 영소문자를 1자 이상 포함해주세요.")
    @Pattern(regexp = ".*[A-Z].*", message = "비밀번호에 영대문자를 1자 이상 포함해주세요.")
    @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", message = "비밀번호에 특수문자를 1자 이상 포함해주세요.")
    private String password;
    private String nickName;
    private String userRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    public MemberEntity toUpdateEntity() {
        return MemberEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickName(nickName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }
}