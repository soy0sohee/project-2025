package com.study.ExCourses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoursesRequestDto {
    private String name;  //수강명
    private String professor;  //교수명
    private String time;  //수업일
    private Integer credits;  //학점
    private Integer capacity;  //정원

}
