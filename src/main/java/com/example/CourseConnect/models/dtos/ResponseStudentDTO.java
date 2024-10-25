package com.example.CourseConnect.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}