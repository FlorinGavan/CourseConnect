package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.TeacherDTO;

import java.util.List;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    void deleteTeacher(Long id);

    List<TeacherDTO> getTeacher();

}