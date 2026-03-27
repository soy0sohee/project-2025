package com.study.ExCourses.controller;

import com.study.ExCourses.dto.CoursesRequestDto;
import com.study.ExCourses.entity.Courses;
import com.study.ExCourses.repository.CoursesRepository;
import com.study.ExCourses.repository.RegistrationsRepository;
import com.study.ExCourses.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ViewController {
    private final CoursesRepository coursesRepository;
    private final RegistrationsRepository registrationsRepository;
    private final RequestService requestService;

    @PostMapping("/")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        result.put("courses", coursesRepository.findAll());
        result.put("registrations", registrationsRepository.findAll());
        return result;
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam Integer courseId) {
        System.out.println("수강신청 courseId: " + courseId);
        requestService.save(courseId);
        return "ok";
    }

    @PostMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestParam Integer courseId) {
        System.out.println("수강취소 registrationId: " + courseId);
        requestService.cancel(courseId);
        return "ok";
    }

    @PostMapping("/manage")
    @ResponseBody
    public List<Courses> manageList() {
        List<Courses> list = coursesRepository.findAll();
        System.out.println(list);
        return list;
    }

    @PostMapping("/manage/update")
    @ResponseBody
    public String update(@RequestParam Integer editId, CoursesRequestDto coursesRequestDto) {
        requestService.manageUpdate(editId, coursesRequestDto);
        return "ok";
    }

    @PostMapping("/manage/save")
    @ResponseBody
    public String save(CoursesRequestDto coursesRequestDto) {
        requestService.manageSave(coursesRequestDto);
        return "ok";
    }

    @PostMapping("/manage/delete")
    @ResponseBody
    public String delete(@RequestParam Integer courseId) {
        requestService.manageDelete(courseId);
        return "ok";
    }
}
