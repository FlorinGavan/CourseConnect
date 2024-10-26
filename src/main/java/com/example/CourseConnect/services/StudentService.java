package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;

import java.util.List;

public interface StudentService {

    ResponseStudentDTO createStudent(RequestStudentDTO requestStudentDTO);

    ResponseStudentDTO getStudentById(Long id);

    List<ResponseStudentDTO> getAllStudents();

    void deleteStudent(Long id);
}