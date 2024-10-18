package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.TeacherCreateException;
import com.example.CourseConnect.models.dtos.RequestTeacherDTO;
import com.example.CourseConnect.models.dtos.ResponseTeacherDTO;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherValidatorService teacherValidatorService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherValidatorService teacherValidatorService, ObjectMapper objectMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherValidatorService = teacherValidatorService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseTeacherDTO createTeacher(RequestTeacherDTO requestTeacherDTO) {
        Teacher teacherEntitySave = objectMapper.convertValue(requestTeacherDTO, Teacher.class);
        teacherValidatorService.validateTeacherDTO(requestTeacherDTO);

        Teacher teacherResponseEntity = teacherRepository.save(teacherEntitySave);
        log.info("Teacher with id {} was created", teacherResponseEntity.getId());
        return objectMapper.convertValue(teacherResponseEntity, ResponseTeacherDTO.class);
    }

    @Override
    public List<ResponseTeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> objectMapper.convertValue(teacher, ResponseTeacherDTO.class))
                .toList();
    }

    @Override
    public ResponseTeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherCreateException("Teacher not found"));
        return objectMapper.convertValue(teacher, ResponseTeacherDTO.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

//    @Override
//    public List<CourseDTO> getCoursesByTeacherId(Long id) {
//        Teacher teacher = teacherRepositories.findById(id)
//                .orElseThrow(() -> new TeacherCreateException("Teacher not found"));
//        return teacher.getCourses().stream()
//                .map(course -> objectMapper.convertValue(course, CourseDTO.class))
//                .toList();
//    }
}