package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.RequestTeacherDTO;
import com.example.CourseConnect.models.dtos.ResponseTeacherDTO;

import java.util.List;

public interface TeacherService {

    ResponseTeacherDTO createTeacher(RequestTeacherDTO requestTeacherDTO);

    void deleteTeacher(Long id);

    ResponseTeacherDTO getTeacherById(Long id);

    List<ResponseTeacherDTO> getAllTeachers();
}