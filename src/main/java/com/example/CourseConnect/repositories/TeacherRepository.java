package com.example.CourseConnect.repositories;

import com.example.CourseConnect.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findTeacherByEmail(String email);

    Optional<Teacher> findTeacherById(Long id);
}