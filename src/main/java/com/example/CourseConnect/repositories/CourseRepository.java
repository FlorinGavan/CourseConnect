package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.entities.Course;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    Optional<Course> findCourseById(@NotNull Long id);
}