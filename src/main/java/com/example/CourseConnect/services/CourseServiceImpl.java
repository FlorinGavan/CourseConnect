package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.repositories.CourseRepositories;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.example.CourseConnect.repositories.TeacherRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
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

        existingCourse.setName(courseDTO.getName() == null ? existingCourse.getName() : courseDTO.getName());
        existingCourse.setCategory(courseDTO.getCategory() == null ? existingCourse.getCategory() : courseDTO.getCategory());
        existingCourse.setDescription(courseDTO.getDescription() == null ? existingCourse.getDescription() : courseDTO.getDescription());
        existingCourse.setCourseScheduleTime(courseDTO.getCourseScheduleTime() == null ? existingCourse.getCourseScheduleTime() : courseDTO.getCourseScheduleTime());
        existingCourse.setCourseDay(courseDTO.getCourseDay() == null ? existingCourse.getCourseDay() : courseDTO.getCourseDay());
        existingCourse.setName(courseDTO.getName() == null ? existingCourse.getName() : courseDTO.getName());

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
    public List<CourseDTO> filterCourses(String name, Category category, DayOfWeek courseDay) {
        Specification<Course> specification = Specification
                .where(CourseSpecification.nameContains(name))
                .and(CourseSpecification.categoryContains(category))
                .and(CourseSpecification.dayOfWeekContains(courseDay));

        List<Course> courses = courseRepositories.findAll(specification);
        log.info("{} courses found", courses.size());

        return courses.stream()
                .map(course -> objectMapper.convertValue(course, CourseDTO.class))
                .toList();
    }

//    @Override
//    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
//        logger.info("Fetching students for course Id: {}", courseId);
//        List<Student> students = studentRepositories.findCoursesByIdOrderByFirstNameAsc(courseId);
//        logger.info("Number of students found: {} ", students.size());
//        return students.stream()
//                .map(student -> objectMapper.convertValue(student, StudentDTO.class))
//                .toList();
//    }

    @Override
    public void deleteCourse(Long id) {
        courseRepositories.deleteById(id);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepositories.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));//use custom exception here
        return objectMapper.convertValue(course, CourseDTO.class);
    }
}