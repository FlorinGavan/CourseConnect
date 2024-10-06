package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO updateCourse = courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok("Course edited");
    }
}