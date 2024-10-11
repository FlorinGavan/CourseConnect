package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.entities.Category;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();

//    List<StudentDTO> getStudentsByCourseId(Long courseId);

    List<CourseDTO>filterCourses(String name, Category category, DayOfWeek courseDay);

    void deleteCourse(Long id);

    CourseDTO getCourseById(Long id);
}