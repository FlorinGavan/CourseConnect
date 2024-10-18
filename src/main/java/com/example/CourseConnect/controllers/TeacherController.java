package com.example.CourseConnect.controllers;

import com.example.CourseConnect.models.dtos.RequestTeacherDTO;
import com.example.CourseConnect.models.dtos.ResponseTeacherDTO;
import com.example.CourseConnect.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<ResponseTeacherDTO> createTeacher(@RequestBody @Valid RequestTeacherDTO requestTeacherDTO) {
        return ResponseEntity.ok(teacherService.createTeacher(requestTeacherDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseTeacherDTO>> getAllTeachers() {
        List<ResponseTeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTeacherDTO> getTeacherById(@PathVariable Long id) {
        ResponseTeacherDTO responseTeacherDTO = teacherService.getTeacherById(id);
        return ResponseEntity.ok(responseTeacherDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted");
    }

//    @GetMapping("/{id}/courses")
//    public ResponseEntity<List<CourseDTO>> getCoursesByTeacherId(@PathVariable Long id) {
//        List<CourseDTO> courses = teacherService.getCoursesByTeacherId(id);
//        return ResponseEntity.ok(courses);
//    }
}