package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositories extends JpaRepository<Student, Long> {
}