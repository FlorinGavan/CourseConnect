package com.example.CourseConnect.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private List<StudentEnrollCourse> studentEnrollCourses;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}