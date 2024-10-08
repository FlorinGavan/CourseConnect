package com.example.CourseConnect.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}