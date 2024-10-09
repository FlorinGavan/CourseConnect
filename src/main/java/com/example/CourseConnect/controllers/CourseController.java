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
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseDTO, courseDTO.getTeacherId()));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseDTO>> getCourseByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(category));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getStudentsByCourseId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok("Course edited");
    }

    /**
     * la course nu pot sa intorc @GetMapping getCourseById si sa imi intoarca acel curs si cu o lista de Studenti care sunt inscrisi la acel curs .
     *
     * eu nu vad unde aveai getCourseById in controller-ul asta...
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

}