package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.RequestStudentDTO;

public interface StudentValidatorService {

    void validateStudentDTO(RequestStudentDTO responseStudentDTO);
}