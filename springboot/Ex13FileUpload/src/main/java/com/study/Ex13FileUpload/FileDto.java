package com.study.Ex13FileUpload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    // uuid
    // : Universally Unique Identifier 약자
    // : 중복될 확률이 거의 없는 128비트 고유 식별자 -> 랜덤 생성함
    // 중복되지 않는 파일 이름 생성 방법
    // 1. 타임스탬프 이용 : filename-20260225124210121.png
    // 2. uuid 이용 : filename-8자-4자-4자-4자-12자.png -> 16진수 문자
    private String uuid;
    private String fileName;
    private String contentTypes;
}
