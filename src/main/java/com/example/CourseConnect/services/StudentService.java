package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

    List<StudentDTO> getAllStudents();

//    void enrollInCourse(Long studentId, Long courseId);

    void deleteStudent(Long id);
}