package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.CourseCreateException;
import com.example.CourseConnect.exceptions.TeacherNotFoundException;
import com.example.CourseConnect.models.dtos.RequestCourseDTO;
import com.example.CourseConnect.models.dtos.ResponseCourseDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.CourseRepository;
import com.example.CourseConnect.repositories.StudentRepository;
import com.example.CourseConnect.repositories.TeacherRepository;
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

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ObjectMapper objectMapper, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public ResponseCourseDTO createCourse(RequestCourseDTO requestCourseDTO) {
        Teacher teacher = teacherRepository.findById(requestCourseDTO.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));

        if (teacher.getCourse()!=null){
           throw new CourseCreateException("This teacher is already assigned to a task");
        }
        Course courseEntitySave = objectMapper.convertValue(requestCourseDTO, Course.class);
        courseEntitySave.setTeacher(teacher);
        Course courseResponseEntity = courseRepository.save(courseEntitySave);

        log.info("Course with id {} was created ", courseResponseEntity.getId());
        return objectMapper.convertValue(courseResponseEntity, ResponseCourseDTO.class);
    }

    @Override
    public ResponseCourseDTO updateCourse(Long id, RequestCourseDTO requestCourseDTO) {
        Course existingCourse = courseRepository.findCourseById(id)
                .orElseThrow(() -> new RuntimeException());

        existingCourse.setName(requestCourseDTO.getName() == null ? existingCourse.getName() : requestCourseDTO.getName());
        existingCourse.setCategory(requestCourseDTO.getCategory() == null ? existingCourse.getCategory() : requestCourseDTO.getCategory());
        existingCourse.setDescription(requestCourseDTO.getDescription() == null ? existingCourse.getDescription() : requestCourseDTO.getDescription());
        existingCourse.setCourseScheduleTime(requestCourseDTO.getCourseScheduleTime() == null ? existingCourse.getCourseScheduleTime() : requestCourseDTO.getCourseScheduleTime());
        existingCourse.setCourseDay(requestCourseDTO.getCourseDay() == null ? existingCourse.getCourseDay() : requestCourseDTO.getCourseDay());
        existingCourse.setCourseRoomSize(requestCourseDTO.getCourseRoomSize() == null ? existingCourse.getCourseRoomSize() : requestCourseDTO.getCourseRoomSize());

        Course updateValue = courseRepository.save(existingCourse);
        log.info("Course with id {} was updated", updateValue.getId());
        return objectMapper.convertValue(updateValue, ResponseCourseDTO.class);
    }

    @Override
    public List<ResponseCourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> objectMapper.convertValue(course, ResponseCourseDTO.class))
                .toList();
    }

    @Override
    public List<ResponseCourseDTO> filterCourses(String name, Category category, DayOfWeek courseDay) {
        Specification<Course> specification = Specification
                .where(CourseSpecification.nameContains(name))
                .and(CourseSpecification.categoryContains(category))
                .and(CourseSpecification.dayOfWeekContains(courseDay));

        List<Course> courses = courseRepository.findAll(specification);
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
        courseRepository.deleteById(id);
    }

}