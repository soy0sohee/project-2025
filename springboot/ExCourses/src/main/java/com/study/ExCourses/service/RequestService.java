package com.study.ExCourses.service;

import com.study.ExCourses.dto.CoursesRequestDto;
import com.study.ExCourses.entity.Courses;
import com.study.ExCourses.entity.Registrations;
import com.study.ExCourses.repository.CoursesRepository;
import com.study.ExCourses.repository.RegistrationsRepository;
import com.sun.nio.sctp.IllegalUnbindException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RegistrationsRepository registrationsRepository;
    private final CoursesRepository coursesRepository;

    @Transactional
    public void save(Integer courseId) {
        Registrations registrations = Registrations.builder()
                .courseId(courseId)
                .registeredAt(LocalDateTime.now())
                .build();
        registrationsRepository.save(registrations);

        Courses courses = coursesRepository.findById(courseId).orElseThrow(()->new IllegalUnbindException("등록된 강좌 없음"));
        Integer enrolled = courses.getEnrolled() + 1;
        courses.updateEnrolled(enrolled);
    }

    @Transactional
    public void cancel(Integer courseId) {
        Registrations registrations = registrationsRepository.findByCourseId(courseId);
        registrationsRepository.delete(registrations);

        Courses courses = coursesRepository.findById(courseId).orElseThrow(()->new IllegalUnbindException("등록된 강좌 없음"));
        Integer enrolled = courses.getEnrolled() - 1;
        courses.updateEnrolled(enrolled);
    }

    @Transactional
    public void manageUpdate(Integer editId, CoursesRequestDto coursesRequestDto) {
        // Optional<Courses> optional = coursesRepository.findById(editId);
        // if(optional.isEmpty()){
        //  throw new IllegalArgumentException("등록된 강좌 없음");
        // }
        Courses courses = coursesRepository.findById(editId).orElseThrow(()->new IllegalUnbindException("등록된 강좌 없음"));

        String name = coursesRequestDto.getName();
        String professor = coursesRequestDto.getProfessor();
        String time = coursesRequestDto.getTime();
        Integer credits = coursesRequestDto.getCredits();
        Integer capacity = coursesRequestDto.getCapacity();

        courses.updateCourses(name, professor, time, credits, capacity);
    }

    @Transactional
    public void manageSave(CoursesRequestDto coursesRequestDto) {
        String name = coursesRequestDto.getName();
        String professor = coursesRequestDto.getProfessor();
        String time = coursesRequestDto.getTime();
        Integer credits = coursesRequestDto.getCredits();
        Integer capacity = coursesRequestDto.getCapacity();

        Courses courses = Courses.builder()
                .name(name)
                .professor(professor)
                .time(time)
                .credits(credits)
                .capacity(capacity)
                .enrolled(0)
                .build();

        coursesRepository.save(courses);
    }

    @Transactional
    public void manageDelete(Integer courseId) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(()->new IllegalUnbindException("등록된 강좌 없음"));
        coursesRepository.delete(courses);
    }
}
