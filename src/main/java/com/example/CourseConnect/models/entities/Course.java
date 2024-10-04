package com.example.CourseConnect.models.entities;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Course {

    private Long id;
    private String name;
    private String department;
    private String description;
    private LocalDateTime localDateTime;
    private DayOfWeek dayOfWeek;

    private int capacity;
}