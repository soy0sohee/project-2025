package com.study.Ex18TDD;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResDto {
    // 예) {'status':'ok','message':'성공'}
    private String status;
    private String message;
}
