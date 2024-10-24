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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ObjectMapper objectMapper) {
        this.teacherRepository = teacherRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseTeacherDTO createTeacher(RequestTeacherDTO requestTeacherDTO) {
        Optional<Teacher> validateEmail = teacherRepository.findTeacherByEmail(requestTeacherDTO.getEmail());

        Teacher teacherEntitySave = objectMapper.convertValue(requestTeacherDTO, Teacher.class);
        if (validateEmail.isPresent()) {
            throw new TeacherCreateException("Email already used. Try again with another email!");
        }

        Teacher teacherResponseEntity = teacherRepository.save(teacherEntitySave);
        log.info("Teacher with id {} was created", teacherResponseEntity.getId());
        return objectMapper.convertValue(teacherResponseEntity, ResponseTeacherDTO.class);
    }

    @Override
    public List<ResponseTeacherDTO> getAllTeachers() {
        List<ResponseTeacherDTO> responseTeacherDTOList = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            ResponseTeacherDTO responseTeacherDTO = objectMapper.convertValue(teacher, ResponseTeacherDTO.class);
            responseTeacherDTOList.add(responseTeacherDTO);
        }
        return responseTeacherDTOList;
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
}