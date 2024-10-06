package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.example.CourseConnect.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {


    private final StudentService studentService;
    private final StudentRepositories studentRepositories;

    public StudentController(StudentService studentService, StudentRepositories studentRepositories) {
        this.studentService = studentService;
        this.studentRepositories = studentRepositories;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }
}