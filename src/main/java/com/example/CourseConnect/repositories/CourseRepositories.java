package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositories extends JpaRepository<Course, Long> {
}