package com.example.CourseConnect.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_enroll_courses")
public class StudentEnrollCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
     @JsonIgnore
    private Student student;
}