package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        return null;
    }

    @Override
    public void deleteTeacher(Long id) {

    }

    @Override
    public List<TeacherDTO> getTeacher() {
        return List.of();
    }
}