package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.repositories.CourseRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositories courseRepositories;
    private final ObjectMapper objectMapper;

    public CourseServiceImpl(CourseRepositories courseRepositories, ObjectMapper objectMapper) {
        this.courseRepositories = courseRepositories;
        this.objectMapper = objectMapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course courseEntitySave = objectMapper.convertValue(courseDTO, Course.class);
        Course courseResponseEntity = courseRepositories.save(courseEntitySave);
        log.info("Course with id {} was created ", courseResponseEntity.getId());
        return objectMapper.convertValue(courseResponseEntity, CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepositories.findCourseById(id)
                .orElseThrow(() -> new RuntimeException());

        if (courseDTO.getName() != null) {
            existingCourse.setName(courseDTO.getName());
        }
        if (courseDTO.getDepartment()!= null){
            existingCourse.setDepartment(courseDTO.getDepartment());
        }
        if (courseDTO.getDescription()!= null){
            existingCourse.setDescription(courseDTO.getDescription());
        }
        if (courseDTO.getLocalDateTime()!=null){
            existingCourse.setLocalDateTime(courseDTO.getLocalDateTime());
        }
        if (courseDTO.getDayOfWeek()!= null){
            existingCourse.setDayOfWeek(courseDTO.getDayOfWeek());
        }
        Course updateValue = courseRepositories.save(existingCourse);
        log.info("Course with id {} was updated", updateValue.getId());
        return objectMapper.convertValue(updateValue, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCourse() {
        List<Course> courses = courseRepositories.findAll();
        return courses.stream()
                .map(course -> objectMapper.convertValue(course, CourseDTO.class))
                .toList();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepositories.deleteById(id);
    }
}