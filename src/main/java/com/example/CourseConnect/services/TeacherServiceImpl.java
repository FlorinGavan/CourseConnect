package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.TeacherDTO;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.example.CourseConnect.repositories.TeacherRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositories teacherRepositories;
    private final  TeacherValidatorService teacherValidatorService;
    private final ObjectMapper objectMapper;

    public TeacherServiceImpl(TeacherRepositories teacherRepositories, TeacherValidatorService teacherValidatorService, ObjectMapper objectMapper) {
        this.teacherRepositories = teacherRepositories;
        this.teacherValidatorService = teacherValidatorService;
        this.objectMapper = objectMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacherEntitySave = objectMapper.convertValue(teacherDTO, Teacher.class);
        teacherValidatorService.validateTeacherDTO(teacherDTO);

        Teacher teacherResponseEntity = teacherRepositories.save(teacherEntitySave);
        log.info("Teacher with id {} was created", teacherResponseEntity.getId());
        return objectMapper.convertValue(teacherResponseEntity, TeacherDTO.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepositories.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getTeacher() {
        List<Teacher> teachers = teacherRepositories.findAll();
        return teachers.stream()
                .map(teacher -> objectMapper.convertValue(teacher, TeacherDTO.class))
                .toList();
    }
}