package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.StudentEnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentEnrollCourseRepository extends JpaRepository<StudentEnrollCourse, Long> {

    Optional<StudentEnrollCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<StudentEnrollCourse> findByStudentId(Long studentId);
}