package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.CourseDTO;
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
    public ResponseEntity<ResponseCourseDTO> createCourse(@RequestBody @Valid RequestCourseDTO requestCourseDTO) {
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
            @RequestParam(value = "courseDay", required = false) DayOfWeek courseDay) {
        return ResponseEntity.ok(courseService.filterCourses(name, category, courseDay));
    }
        @GetMapping("/students/{studentId}")
    public ResponseEntity<List<ResponseCourseDTO>> getCoursesByStudentId(@PathVariable Long studentId) {
        List<ResponseCourseDTO> courses = courseService.getCoursesByStudentId(studentId);
        return ResponseEntity.ok(courses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok("Course edited");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id, @RequestParam Long teacherId) {
        courseService.deleteCourse(id, teacherId);
        return ResponseEntity.ok("Course deleted successfully.");
    }
}