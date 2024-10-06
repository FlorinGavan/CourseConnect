package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositories studentRepositories;
    private final StudentValidatorService studentValidatorService;
    private final ObjectMapper objectMapper;


    public StudentServiceImpl(StudentRepositories studentRepositories,StudentValidatorService studentValidatorService , ObjectMapper objectMapper) {
        this.studentRepositories = studentRepositories;
        this.studentValidatorService = studentValidatorService;
        this.objectMapper = objectMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student studentEntitySave = objectMapper.convertValue(studentDTO, Student.class);
        studentValidatorService.validateStudentDTO(studentDTO);

        Student studentResponseEntity = studentRepositories.save(studentEntitySave);
        log.info("Student created with id: {}", studentResponseEntity.getId());
        return objectMapper.convertValue(studentResponseEntity, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepositories.findAll();
        return students.stream()
                .map(student -> objectMapper.convertValue(student, StudentDTO.class))
                .toList();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepositories.deleteById(id);
    }
}