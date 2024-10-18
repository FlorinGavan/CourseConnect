package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.TeacherCreateException;
import com.example.CourseConnect.models.dtos.RequestTeacherDTO;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherValidatorServiceImpl implements TeacherValidatorService {

    public final TeacherRepository teacherRepository;

    public TeacherValidatorServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void validateTeacherDTO(RequestTeacherDTO requestTeacherDTO) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(requestTeacherDTO.getEmail());

        if (requestTeacherDTO.getFirstName().isEmpty()) {
            throw new TeacherCreateException("First name is required");
        }
        if (requestTeacherDTO.getLastName().isEmpty()) {
            throw new TeacherCreateException("Last name is required");
        }
        if (requestTeacherDTO.getFirstName().length() < 2) {
            throw new TeacherCreateException("First name is too short");
        }
        if (requestTeacherDTO.getLastName().length() < 2) {
            throw new TeacherCreateException("Last name is too short");
        }
        if (!requestTeacherDTO.getFirstName().matches("[a-zA-Z]+") || !requestTeacherDTO.getLastName().matches("[a-zA-Z]+")) {
            throw new TeacherCreateException("Numbers and Symbols are not allowed");
        }
        if (requestTeacherDTO.getEmail().isEmpty()) {
            throw new TeacherCreateException("Please add an email ");
        }
        if (teacher.isPresent()) {
            throw new TeacherCreateException("Email already used. Try again with another email!");
        }
    }
}