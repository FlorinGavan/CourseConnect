package com.example.CourseConnect.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "description")
    private String description;

    //what this property does?
    @Column(name = "course_schedule_time")
    private LocalDateTime courseScheduleTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_day")
    private DayOfWeek courseDay;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "teacher_id", nullable = false)
//    private Teacher teacher;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "course_student",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id"))
//    private Set<Student> students = new HashSet<>();
//
}