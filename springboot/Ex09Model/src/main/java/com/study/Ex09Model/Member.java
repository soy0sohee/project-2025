package com.study.Ex09Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

// ORM(Object Relation Mapping) : 객체와 Request 파라미터 매핑
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    // input태그 name속성과 동일, ORM(Object Relation Mapping)맵핑
    String username;
    String password;
}
