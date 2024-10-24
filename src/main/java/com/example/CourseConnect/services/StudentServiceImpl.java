package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.CourseRepository;
import com.example.CourseConnect.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository,  ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseStudentDTO createStudent(RequestStudentDTO requestStudentDTO) {
        Optional<Student> validateEmail = studentRepository.findStudentByEmail(requestStudentDTO.getEmail());
        Student studentEntitySave = objectMapper.convertValue(requestStudentDTO, Student.class);
        if (validateEmail.isPresent()){
             throw new StudentCreateException("Email already used. Try again with another email!");
        }
        Student studentResponseEntity = studentRepository.save(studentEntitySave);
        log.info("Student created with id: {}", studentResponseEntity.getId());
        return objectMapper.convertValue(studentResponseEntity, ResponseStudentDTO.class);
    }

    @Override
    public ResponseStudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentCreateException("Student not found"));
        return objectMapper.convertValue(student, ResponseStudentDTO.class);
    }

    @Override
    public List<ResponseStudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
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
        studentRepository.deleteById(id);
    }
}