package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.RequestTeacherDTO;

public interface TeacherValidatorService {

    void validateTeacherDTO(RequestTeacherDTO requestTeacherDTO);
}