package com.example.CourseConnect.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RequestTeacherDTO {

    @NotBlank(message = "First name should not be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name cannot contain numbers or symbols")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name cannot contain numbers or symbols")
    private String lastName;

    @NotBlank(message = "You must add an email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    private String email;
}