package com.example.CourseConnect.models.dtos;

import com.example.CourseConnect.models.entities.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class RequestCourseDTO {

    @NotBlank(message = "This field is mandatory")
    private String name;

    private Category category;
    private String description;
    private LocalDateTime courseScheduleTime;
    private DayOfWeek courseDay;

    @Min(value = 2, message = "Size of room cannot be less then 2")
    private Integer courseRoomSize;

    @NotNull(message = "Teacher ID cannot be null")
    private Long teacherId;
}