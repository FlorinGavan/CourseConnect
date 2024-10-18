package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;
import com.example.CourseConnect.models.entities.StudentEnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollCourseRepository extends JpaRepository<StudentEnrollCourse, Long> {
}