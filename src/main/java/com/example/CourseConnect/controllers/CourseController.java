package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.RequestCourseDTO;
import com.example.CourseConnect.models.dtos.ResponseCourseDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ResponseCourseDTO> createCourse(@RequestBody RequestCourseDTO requestCourseDTO) {
        return ResponseEntity.ok(courseService.createCourse(requestCourseDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @GetMapping("/filter")
    public ResponseEntity<List<ResponseCourseDTO>> filterCourses(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "genre", required = false) DayOfWeek courseDay) {
        return ResponseEntity.ok(courseService.filterCourses(name, category, courseDay));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody RequestCourseDTO requestCourseDTO) {
        courseService.updateCourse(id, requestCourseDTO);
        return ResponseEntity.ok("Course edited");
    }
}
