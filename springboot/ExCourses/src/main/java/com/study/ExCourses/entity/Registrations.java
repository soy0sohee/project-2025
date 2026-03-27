package com.study.ExCourses.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="registrations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="registration_id")
    private Integer registrationId;
    @Column(name="course_id")
    private Integer courseId;
    @Column(name="registered_at")
    private LocalDateTime registeredAt;
}
