package com.example.CourseConnect.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

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


    @Column(name = "course_schedule_time")
    private LocalDateTime courseScheduleTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_day")
    private DayOfWeek courseDay;

    @Column(name = "course_room_size")
    private Integer courseRoomSize;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentEnrollCourse> studentEnrollCourses;

    @ManyToOne
    @JoinColumn(name = "teacher_id", unique = true)
    private Teacher teacher;
}