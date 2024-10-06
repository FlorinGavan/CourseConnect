package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.TeacherDTO;
import com.example.CourseConnect.repositories.TeacherRepositories;
import com.example.CourseConnect.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepositories teacherRepositories;

    public TeacherController(TeacherService teacherService, TeacherRepositories teacherRepositories) {
        this.teacherService = teacherService;
        this.teacherRepositories = teacherRepositories;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(teacherService.createTeacher(teacherDTO));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getTeachers() {
        return ResponseEntity.ok(teacherService.getTeacher());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted");
    }
}