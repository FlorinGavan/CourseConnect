package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.models.dtos.CourseDTO;
import com.example.CourseConnect.models.dtos.StudentDTO;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.CourseRepositories;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositories studentRepositories;
    private final CourseRepositories courseRepositories;
    private final StudentValidatorService studentValidatorService;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepositories studentRepositories, CourseRepositories courseRepositories, StudentValidatorService studentValidatorService, ObjectMapper objectMapper) {
        this.studentRepositories = studentRepositories;
        this.courseRepositories = courseRepositories;
        this.studentValidatorService = studentValidatorService;
        this.objectMapper = objectMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student studentEntitySave = objectMapper.convertValue(studentDTO, Student.class);
        studentValidatorService.validateStudentDTO(studentDTO);

        Student studentResponseEntity = studentRepositories.save(studentEntitySave);
        log.info("Student created with id: {}", studentResponseEntity.getId());
        return objectMapper.convertValue(studentResponseEntity, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepositories.findById(id).orElseThrow(()-> new StudentCreateException("Student not found"));
        return objectMapper.convertValue(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepositories.findAll().stream()
                .map(student -> objectMapper.convertValue(student, StudentDTO.class))
                .toList();
    }

//    @Override
//    @Transactional
//    public void enrollInCourse(Long studentId, Long courseId) {
//        logger.info("Enrolling student ID {} in course ID {}", studentId, courseId);
//        Student student = studentRepositories.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//        Course course = courseRepositories.findById(courseId)
//                .orElseThrow(() -> new RuntimeException("Course not found"));
//        student.getCourses().add(course);
//        studentRepositories.save(student);
//        logger.info("Enrollment successful");
//    }

    @Override
    public void deleteStudent(Long id) {
        studentRepositories.deleteById(id);
    }
}