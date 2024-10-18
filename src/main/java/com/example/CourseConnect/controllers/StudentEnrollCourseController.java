package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;
import com.example.CourseConnect.services.StudentEnrollCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enroll")
public class StudentEnrollCourseController {

    private final StudentEnrollCourseService studentEnrollCourseService;

    public StudentEnrollCourseController(StudentEnrollCourseService studentEnrollCourseService) {
        this.studentEnrollCourseService = studentEnrollCourseService;
    }

    @PostMapping
    public ResponseEntity<String> enrollCourse(@RequestBody StudentEnrollCourseDTO studentEnrollCourseDTO) {
        studentEnrollCourseService.enrollCourse(studentEnrollCourseDTO);
        return ResponseEntity.ok("Student enrolled successful");
    }
}