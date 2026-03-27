package com.study.ExCourses.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer courseId;
    private String name;  //수강명
    private String professor;  //교수명
    private String time;  //수업일
    private Integer credits;  //학점
    private Integer capacity;  //정원
    private Integer enrolled = 0;  //신청인원

    public void updateEnrolled(Integer enrolled) {
        this.enrolled = enrolled;
    }

    public void updateCourses(String name, String professor, String time, Integer credits, Integer capacity) {
        this.name = name;
        this.professor = professor;
        this.time = time;
        this.credits = credits;
        this.capacity = capacity;
    }
}
