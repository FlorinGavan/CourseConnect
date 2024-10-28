package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;

import java.util.List;

public interface StudentEnrollCourseService {

    void enrollCourse(StudentEnrollCourseDTO studentEnrollCourseDTO);

    List<StudentEnrollCourseDTO> getAllEnrollments();
}