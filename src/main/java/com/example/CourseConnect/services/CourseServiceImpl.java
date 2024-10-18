package com.example.CourseConnect.services;

import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.RequestCourseDTO;
import com.example.CourseConnect.models.dtos.ResponseCourseDTO;
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
    public ResponseCourseDTO createCourse(RequestCourseDTO requestCourseDTO) {
        Course courseEntitySave = objectMapper.convertValue(requestCourseDTO, Course.class);
        Course courseResponseEntity = courseRepositories.save(courseEntitySave);

        log.info("Course with id {} was created ", courseResponseEntity.getId());
        return objectMapper.convertValue(courseResponseEntity, ResponseCourseDTO.class);
    }

    @Override
    public ResponseCourseDTO updateCourse(Long id, RequestCourseDTO requestCourseDTO) {
        Course existingCourse = courseRepositories.findCourseById(id)
                .orElseThrow(() -> new RuntimeException());

        existingCourse.setName(requestCourseDTO.getName() == null ? existingCourse.getName() : requestCourseDTO.getName());
        existingCourse.setCategory(requestCourseDTO.getCategory() == null ? existingCourse.getCategory() : requestCourseDTO.getCategory());
        existingCourse.setDescription(requestCourseDTO.getDescription() == null ? existingCourse.getDescription() : requestCourseDTO.getDescription());
        existingCourse.setCourseScheduleTime(requestCourseDTO.getCourseScheduleTime() == null ? existingCourse.getCourseScheduleTime() : requestCourseDTO.getCourseScheduleTime());
        existingCourse.setCourseDay(requestCourseDTO.getCourseDay() == null ? existingCourse.getCourseDay() : requestCourseDTO.getCourseDay());
        existingCourse.setCourseRoomSize(requestCourseDTO.getCourseRoomSize() == null ? existingCourse.getCourseRoomSize(): requestCourseDTO.getCourseRoomSize());

        Course updateValue = courseRepositories.save(existingCourse);
        log.info("Course with id {} was updated", updateValue.getId());
        return objectMapper.convertValue(updateValue, ResponseCourseDTO.class);
    }

    @Override
    public List<ResponseCourseDTO> getAllCourses() {
        return courseRepositories.findAll().stream()
                .map(course -> objectMapper.convertValue(course, ResponseCourseDTO.class))
                .toList();
    }

    @Override
    public List<ResponseCourseDTO> filterCourses(String name, Category category, DayOfWeek courseDay) {
        Specification<Course> specification = Specification
                .where(CourseSpecification.nameContains(name))
                .and(CourseSpecification.categoryContains(category))
                .and(CourseSpecification.dayOfWeekContains(courseDay));

        List<Course> courses = courseRepositories.findAll(specification);
        log.info("{} courses found", courses.size());

        return courses.stream()
                .map(course -> objectMapper.convertValue(course, ResponseCourseDTO.class))
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

}