package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.entities.Category;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();

    List<CourseDTO> getCoursesByCategory(Category category);

//    List<StudentDTO> getStudentsByCourseId(Long courseId);

    void deleteCourse(Long id);

    CourseDTO getCourseById(Long id);
}