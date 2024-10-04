package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public List<StudentDTO> getStudents() {
        return List.of();
    }

    @Override
    public void deleteStudent(Long id) {

    }
}