package com.example.CourseConnect.models.dtos;

import lombok.Data;

@Data
public class ResponseStudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}