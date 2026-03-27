package com.study.Ex18TDD;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode // 객체비교함수
public class MemberDto {
    private String loginId;
    private String loginPw;
}
