package com.example.CourseConnect.models.dtos;

import com.example.CourseConnect.models.entities.Category;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class ResponseCourseDTO {

    private Long id;
     private String name;
    private Category category;
    private String description;
    private LocalDateTime courseScheduleTime;
    private DayOfWeek courseDay;
}