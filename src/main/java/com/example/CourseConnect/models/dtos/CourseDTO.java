package com.example.CourseConnect.models.dtos;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class CourseDTO {


    private Long id;
    private String name;
    private String department;
    private String description;
    private LocalDateTime localDateTime;
    private DayOfWeek dayOfWeek;
}