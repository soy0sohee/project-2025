package com.study.Ex05Lombok;

import jakarta.annotation.Nullable; // 이전 javax 패키지를 rename한 것, 자바 기능 향상 패키지
import lombok.*; // Lombok을 사용하면 자동생성 됨
import org.springframework.stereotype.Component;

// 롬복이 지원하는 애너테이션 목록
// @Getter : getter 자동생성
// @Setter : setter 자동생성
// @ToString : toString 메소드 자동생성
// @EqualsAndHashCode : equals, hashCode 메서드 생성
// @NoArgsConstructor(force = true) : 매개변수 없는 기본생성자 자동생성
//                                  : force옵션 -> final필드가 존재할 때 강제 초기화해주는 옵션 / force = true -> null
// @AllArgsConstructor : 모든 필드를 파라미터로 받는 생성자 자동생성
// @RequiredArgsConstructor : final이나 @NonNull인 필드만 매개변수로 받는 생성자 자동생성
//                          : 생성자 주입에 사용
// - @NonNull : null을 허용하지 않는 객체 Bean 자동생성
// - @Nullable : null을 허용하는 객체 Bean 자동생성,
//             : jakarta.annotation.Nullable
//             : javax -> jakarta  java이름의 라이센스 때문에
// @Data : @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정해주는 어노테이션

@Component
// Bean으로 생성하기 위해서는 기본생성자, 필드생성자, Getter/Setter생성자 넣어야 함
// @Getter
// @Setter
@NoArgsConstructor(force = true) // Member()
@AllArgsConstructor // Member(String, Integer, String, String)
// @RequiredArgsConstructor // Member(String, Integer, String)
@Data
public class Member {
    private final String name;
    private final Integer age; // final필드는 반드시 초기화해서 사용해야 함
    @NonNull // Null을 허용하지 않는 필드 생성
    private String phone;
    @Nullable // Null을 허용하는 필드 생성
    private String email;
}
