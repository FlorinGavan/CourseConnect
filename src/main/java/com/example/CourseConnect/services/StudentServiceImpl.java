package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.CourseRepositories;
import com.example.CourseConnect.repositories.StudentRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseStudentDTO createStudent(RequestStudentDTO requestStudentDTO) {
        Student studentEntitySave = objectMapper.convertValue(requestStudentDTO, Student.class);
        studentValidatorService.validateStudentDTO(requestStudentDTO);

        Student studentResponseEntity = studentRepositories.save(studentEntitySave);
        log.info("Student created with id: {}", studentResponseEntity.getId());
        return objectMapper.convertValue(studentResponseEntity, ResponseStudentDTO.class);
    }

    @Override
    public ResponseStudentDTO getStudentById(Long id) {
        Student student = studentRepositories.findById(id).orElseThrow(()-> new StudentCreateException("Student not found"));
        return objectMapper.convertValue(student, ResponseStudentDTO.class);
    }

    @Override
    public List<ResponseStudentDTO> getAllStudents() {
        return studentRepositories.findAll().stream()
                .map(student -> objectMapper.convertValue(student, ResponseStudentDTO.class))
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