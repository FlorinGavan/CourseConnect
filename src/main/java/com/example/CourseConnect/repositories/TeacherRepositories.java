package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepositories extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findTeacherByEmail(String email);

    List<Teacher> findCoursesByTeacherId(@Param("courseId") Long id);
}