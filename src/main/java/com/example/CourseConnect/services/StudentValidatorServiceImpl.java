package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.StudentRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentValidatorServiceImpl implements StudentValidatorService {

    private final StudentRepositories studentRepositories;

    public StudentValidatorServiceImpl(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    @Override
    public void validateStudentDTO(StudentDTO studentDTO) {
        Optional<Student> student = studentRepositories.findStudentByEmail(studentDTO.getEmail());

        if (studentDTO.getFirstName().isEmpty()) {
            throw new StudentCreateException("First name is required");
        }
        if (studentDTO.getLastName().isEmpty()) {
            throw new StudentCreateException("Last name is required");
        }
        if (studentDTO.getFirstName().length() < 2) {
            throw new StudentCreateException("First name is too short");
        }
        if (studentDTO.getLastName().length() < 2) {
            throw new StudentCreateException("Last name is too short");
        }
        if (!studentDTO.getFirstName().matches("[a-zA-Z]+") || !studentDTO.getLastName().matches("[a-zA-Z]+")) {
            throw new StudentCreateException("Numbers and Symbols are not allowed");
        }
        if (studentDTO.getEmail().isEmpty()) {
            throw new StudentCreateException("Please add an email ");
        }
        if (student.isPresent()) {
            throw new StudentCreateException("Email already used. Try again with another email!");
        }
    }
}