package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepositories extends JpaRepository<Course, Long> {

    List<Course> findByCategory(Category category);

    Optional<Course> findCourseById(@NotNull Long id);
}