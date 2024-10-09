package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.TeacherCreateException;
import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.CourseRepositories;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.example.CourseConnect.repositories.TeacherRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositories courseRepositories;
    private final ObjectMapper objectMapper;
    private final TeacherRepositories teacherRepositories;
    private final StudentRepositories studentRepositories;

    public CourseServiceImpl(CourseRepositories courseRepositories, ObjectMapper objectMapper, TeacherRepositories teacherRepositories, StudentRepositories studentRepositories) {
        this.courseRepositories = courseRepositories;
        this.objectMapper = objectMapper;
        this.teacherRepositories = teacherRepositories;
        this.studentRepositories = studentRepositories;
    }

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO, Long teacherId) {
        Teacher teacher = teacherRepositories.findById(teacherId).orElseThrow(() -> {
            logger.error("Teacher with id {} not found ", teacherId);
            return new TeacherCreateException("Teacher not found");
        });

        Course courseEntitySave = objectMapper.convertValue(courseDTO, Course.class);
        courseEntitySave.setTeacher(teacher);
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
        if (courseDTO.getCategory() != null) {
            existingCourse.setCategory(courseDTO.getCategory());
        }
        if (courseDTO.getDescription() != null) {
            existingCourse.setDescription(courseDTO.getDescription());
        }
        if (courseDTO.getLocalDateTime() != null) {
            existingCourse.setLocalDateTime(courseDTO.getLocalDateTime());
        }
        if (courseDTO.getDayOfWeek() != null) {
            existingCourse.setDayOfWeek(courseDTO.getDayOfWeek());
        }
        Course updateValue = courseRepositories.save(existingCourse);
        log.info("Course with id {} was updated", updateValue.getId());
        return objectMapper.convertValue(updateValue, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepositories.findAll().stream()
                .map(course -> objectMapper.convertValue(course, CourseDTO.class))
                .toList();
    }

    @Override
    public List<CourseDTO> getCoursesByCategory(Category category) {
        return courseRepositories.findByCategory(category).stream()
                .map(course -> objectMapper.convertValue(course, CourseDTO.class))
                .toList();
    }

    @Override
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        logger.info("Fetching students for course Id: {}", courseId);
        List<Student> students = studentRepositories.findCoursesByIdOrderByFirstNameAsc(courseId);
        logger.info("Number of students found: {} ", students.size());
        return students.stream()
                .map(student -> objectMapper.convertValue(student, StudentDTO.class))
                .toList();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepositories.deleteById(id);
    }
}