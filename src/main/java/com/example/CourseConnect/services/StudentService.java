package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getStudents();

    void deleteStudent(Long id);
}