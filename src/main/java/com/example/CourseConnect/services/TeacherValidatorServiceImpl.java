package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.TeacherCreateException;
import com.example.CourseConnect.models.dtos.TeacherDTO;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.TeacherRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherValidatorServiceImpl implements TeacherValidatorService {

    public final TeacherRepositories teacherRepositories;

    public TeacherValidatorServiceImpl(TeacherRepositories teacherRepositories) {
        this.teacherRepositories = teacherRepositories;
    }

    @Override
    public void validateTeacherDTO(TeacherDTO teacherDTO) {
        Optional<Teacher> teacher = teacherRepositories.findTeacherByEmail(teacherDTO.getEmail());

        if (teacherDTO.getFirstName().isEmpty()) {
            throw new TeacherCreateException("First name is required");
        }
        if (teacherDTO.getLastName().isEmpty()) {
            throw new TeacherCreateException("Last name is required");
        }
        if (teacherDTO.getFirstName().length() < 2) {
            throw new TeacherCreateException("First name is too short");
        }
        if (teacherDTO.getLastName().length() < 2) {
            throw new TeacherCreateException("Last name is too short");
        }
        if (!teacherDTO.getFirstName().matches("[a-zA-Z]+") || !teacherDTO.getLastName().matches("[a-zA-Z]+")) {
            throw new TeacherCreateException("Numbers and Symbols are not allowed");
        }
        if (teacherDTO.getEmail().isEmpty()) {
            throw new TeacherCreateException("Please add an email ");
        }
        if (teacher.isPresent()) {
            throw new TeacherCreateException("Email already used. Try again with another email!");
        }
    }
}