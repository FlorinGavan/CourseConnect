package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createCourse = courseService.createCourse(courseDTO, courseDTO.getTeacherId());
        return ResponseEntity.ok(createCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseDTO>> getCourseByCategory(@PathVariable Category category) {
        List<CourseDTO> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(@PathVariable Long id) {
        List<StudentDTO> students = courseService.getStudentsByCourseId(id);
        return ResponseEntity.ok(students);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO updateCourse = courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok("Course edited");
    }
}