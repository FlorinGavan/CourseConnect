package com.example.CourseConnect.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
//    private Set<Long> courseIds;
//    private List<CourseDTO> enrolledCourses;
}