package com.example.CourseConnect.models.dtos;

import com.example.CourseConnect.models.entities.Category;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class RequestCourseDTO {

    private String name;
    private Category category;
    private String description;
    private LocalDateTime courseScheduleTime;
    private DayOfWeek courseDay;
    private Integer courseRoomSize;
    private Long teacherId;
}