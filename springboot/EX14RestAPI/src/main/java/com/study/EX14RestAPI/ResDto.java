package com.study.EX14RestAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResDto {
    // {"status":"ok", "message":"로그인 성공"}
    private String status;
    private String message;
}
