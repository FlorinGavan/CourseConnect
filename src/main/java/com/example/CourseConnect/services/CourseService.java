package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    List<CourseDTO> getCourse();

    void deleteCourse(Long id);
}