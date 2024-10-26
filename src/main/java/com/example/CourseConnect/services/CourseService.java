package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.RequestCourseDTO;
import com.example.CourseConnect.models.dtos.ResponseCourseDTO;
import com.example.CourseConnect.models.entities.Category;

import java.time.DayOfWeek;
import java.util.List;

public interface CourseService {

    ResponseCourseDTO createCourse(RequestCourseDTO requestCourseDTO);

    ResponseCourseDTO updateCourse(Long courseId, CourseDTO courseDTO);

    List<ResponseCourseDTO> getAllCourses();

    List<ResponseCourseDTO> filterCourses(String name, Category category, DayOfWeek courseDay);

    List<ResponseCourseDTO> getCoursesByStudentId(Long studentId);

    void deleteCourse(Long id, Long teacherId);

}