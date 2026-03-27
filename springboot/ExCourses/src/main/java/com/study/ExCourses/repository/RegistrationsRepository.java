package com.study.ExCourses.repository;

import com.study.ExCourses.entity.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registrations, Integer> {
    Registrations findByCourseId(Integer courseId);
}
