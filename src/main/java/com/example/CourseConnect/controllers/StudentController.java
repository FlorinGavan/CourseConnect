package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    /**
     * si la student incerc  sa il conectez la mai multe Cursuri si cand incerc sa fac @GetMapping
     * getStudentById nu merge , si as vrea sa imi intoarca acel student si o lista de cursuri la care este  enroll
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.enrollInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled in course successfully");
    }
}