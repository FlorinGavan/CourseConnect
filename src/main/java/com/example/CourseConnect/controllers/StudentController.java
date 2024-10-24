package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;
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
    public ResponseEntity<ResponseStudentDTO> createStudent(@RequestBody @Valid RequestStudentDTO requestStudentDTO) {
        return ResponseEntity.ok(studentService.createStudent(requestStudentDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseStudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }
}