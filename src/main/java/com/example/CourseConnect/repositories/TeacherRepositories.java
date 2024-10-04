package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepositories extends JpaRepository<Teacher, Long> {
}