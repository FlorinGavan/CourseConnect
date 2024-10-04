package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        return null;
    }

    @Override
    public List<CourseDTO> getCourse() {
        return List.of();
    }

    @Override
    public void deleteCourse(Long id) {

    }
}