package com.example.CourseConnect.models.dtos;

import com.example.CourseConnect.models.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private LocalDateTime localDateTime;
    private DayOfWeek dayOfWeek;
    private Long teacherId;
    private List<StudentDTO> students;
}