package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.*;
import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.RequestCourseDTO;
import com.example.CourseConnect.models.dtos.ResponseCourseDTO;
import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.StudentEnrollCourse;
import com.example.CourseConnect.models.entities.Teacher;
import com.example.CourseConnect.repositories.CourseRepository;
import com.example.CourseConnect.repositories.StudentEnrollCourseRepository;
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
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentEnrollCourseRepository studentEnrollCourseRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ObjectMapper objectMapper, TeacherRepository teacherRepository, StudentRepository studentRepository, StudentEnrollCourseRepository studentEnrollCourseRepository) {
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.studentEnrollCourseRepository = studentEnrollCourseRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public ResponseCourseDTO createCourse(RequestCourseDTO requestCourseDTO) {
        Teacher teacher = teacherRepository.findById(requestCourseDTO.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
        Optional<Course> uniqueCourse = courseRepository.findCourseByName(requestCourseDTO.getName());

        Course courseEntitySave = objectMapper.convertValue(requestCourseDTO, Course.class);
        courseEntitySave.setTeacher(teacher);

        if (uniqueCourse.isPresent()) {
            throw new CourseCreateException("This course name already exists, Try again with another name!");
        }

        Course courseResponseEntity = courseRepository.save(courseEntitySave);
        log.info("Course with id {} was created ", courseResponseEntity.getId());
        log.info("Received teacherId: {}", requestCourseDTO.getTeacherId());

        ResponseCourseDTO responseDto = objectMapper.convertValue(courseResponseEntity, ResponseCourseDTO.class);
        responseDto.setTeacherId(teacher.getId());
        return responseDto;
    }

    @Override
    public ResponseCourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findCourseById(id)
                .orElseThrow(() -> new RuntimeException());

        updateCourseFields(courseDTO, existingCourse);

        Course updateValue = courseRepository.save(existingCourse);
        log.info("Course with id {} was updated", updateValue.getId());
        return objectMapper.convertValue(updateValue, ResponseCourseDTO.class);
    }

    @Override
    public List<ResponseCourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToResponseCourseDTO)
                .toList();
    }

    public List<ResponseCourseDTO> getCoursesByStudentId(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
        }

        List<StudentEnrollCourse> enrollments = studentEnrollCourseRepository.findByStudentId(studentId);
        if (enrollments.isEmpty()) {
            throw new NoCoursesEnrolledException("Student with ID " + studentId + " is not enrolled in any courses.");
        }

        return enrollments.stream()
                .map(StudentEnrollCourse::getCourse)
                .map(course -> {
                    ResponseCourseDTO dto = objectMapper.convertValue(course, ResponseCourseDTO.class);

                    if (course.getTeacher() != null) {
                        Optional<Teacher> teacher = teacherRepository.findTeacherById(course.getTeacher().getId());
                        teacher.ifPresent(t -> dto.setTeacherId(t.getId()));
                    }
                    return dto;
                })
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
                .map(this::mapToResponseCourseDTO)
                .toList();
    }

    @Override
    public void deleteCourse(Long id, Long teacherId) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            if (course.getTeacher().getId().equals(teacherId)) {
                courseRepository.delete(course);
            } else {
                throw new TeacherNotFoundException("You do not have permission to delete this course.");
            }
        } else {
            throw new CourseNotFoundException("Course not found.");
        }
    }

    private void updateCourseFields(CourseDTO courseDTO, Course existingCourse) {
        if (courseDTO.getTeacherId() != null) {
            throw new UnmodifiableFieldException("Cannot modify teacherId field");
        }

        existingCourse.setName(courseDTO.getName() == null ? existingCourse.getName() : courseDTO.getName());
        existingCourse.setCategory(courseDTO.getCategory() == null ? existingCourse.getCategory() : courseDTO.getCategory());
        existingCourse.setDescription(courseDTO.getDescription() == null ? existingCourse.getDescription() : courseDTO.getDescription());
        existingCourse.setCourseScheduleTime(courseDTO.getCourseScheduleTime() == null ? existingCourse.getCourseScheduleTime() : courseDTO.getCourseScheduleTime());
        existingCourse.setCourseDay(courseDTO.getCourseDay() == null ? existingCourse.getCourseDay() : courseDTO.getCourseDay());
        existingCourse.setCourseRoomSize(courseDTO.getCourseRoomSize() == null ? existingCourse.getCourseRoomSize() : courseDTO.getCourseRoomSize());
    }

    private ResponseCourseDTO mapToResponseCourseDTO(Course course) {
        ResponseCourseDTO dto = objectMapper.convertValue(course, ResponseCourseDTO.class);

        if (course.getTeacher() != null) {
            Optional<Teacher> teacher = teacherRepository.findTeacherById(course.getTeacher().getId());
            teacher.ifPresent(t -> dto.setTeacherId(t.getId()));
        }
        return dto;
    }
}