package com.study.EX14RestAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // get, set, reqArgcon
@NoArgsConstructor
public class ReqDto {
    // input태그의 name속성과 매칭
    private String username;
    private String password;
}
