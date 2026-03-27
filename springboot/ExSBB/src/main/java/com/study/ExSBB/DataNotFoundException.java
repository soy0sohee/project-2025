package com.study.ExSBB;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//데이터베이스에서 특정 엔티티 또는 데이터를 찾을 수 없을 때 발생시키는 예외 클래스
//value = HttpStatus.NOT_FOUND : HTTP 상태코드
//reason = "entity not found" : 클라이언트에게 반환 될 예외 이유 응답 메세지 -> 404 오류 반환
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException { //사용자 정의 예외 클래스를 정의하는 방법, 실행 시 발생하는 예외
    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
