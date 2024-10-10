package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Student;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepositories extends JpaRepository<Student, Long> {

//    List<Student> findCoursesByIdOrderByFirstNameAsc(Long courseId);

    Optional<Student> findStudentByEmail(String email);
}