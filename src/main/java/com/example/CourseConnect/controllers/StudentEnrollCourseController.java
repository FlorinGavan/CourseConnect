package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;
import com.example.CourseConnect.services.StudentEnrollCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enroll")
public class StudentEnrollCourseController {

    private final StudentEnrollCourseService studentEnrollCourseService;

    public StudentEnrollCourseController(StudentEnrollCourseService studentEnrollCourseService) {
        this.studentEnrollCourseService = studentEnrollCourseService;
    }

    @PostMapping
    public ResponseEntity<String> studentEnrollCourse(@RequestBody StudentEnrollCourseDTO studentEnrollCourseDTO) {
        studentEnrollCourseService.enrollCourse(studentEnrollCourseDTO);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    @GetMapping
    public ResponseEntity<List<StudentEnrollCourseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(studentEnrollCourseService.getAllEnrollments());
    }
}