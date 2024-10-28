package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.CourseNotFoundException;
import com.example.CourseConnect.exceptions.CourseRoomSizeException;
import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.exceptions.StudentNotFoundException;
import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.models.entities.StudentEnrollCourse;
import com.example.CourseConnect.repositories.CourseRepository;
import com.example.CourseConnect.repositories.StudentEnrollCourseRepository;
import com.example.CourseConnect.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentEnrollCourseServiceImpl implements StudentEnrollCourseService {

    private final StudentEnrollCourseRepository studentEnrollCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    public StudentEnrollCourseServiceImpl(StudentEnrollCourseRepository studentEnrollCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository, ObjectMapper objectMapper) {
        this.studentEnrollCourseRepository = studentEnrollCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enrollCourse(StudentEnrollCourseDTO studentEnrollCourseDTO) {
        Course course = courseRepository.findById(studentEnrollCourseDTO.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (course.getCourseRoomSize() <= 0) {
            throw new CourseRoomSizeException("Course room is full!");
        }

        Student student = studentRepository.findById(studentEnrollCourseDTO.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Optional<StudentEnrollCourse> existingEnrollment = studentEnrollCourseRepository
                .findByStudentIdAndCourseId(student.getId(), course.getId());

        if (existingEnrollment.isPresent()) {
            throw new StudentCreateException("Student is already enrolled in this course.");
        }

        StudentEnrollCourse studentEnrollCourse = new StudentEnrollCourse();
        studentEnrollCourse.setCourse(course);
        studentEnrollCourse.setStudent(student);

        course.setCourseRoomSize(course.getCourseRoomSize() - 1);
        courseRepository.save(course);

        studentEnrollCourseRepository.save(studentEnrollCourse);
        log.info("Student has successfully enrolled in this course.");
    }

    @Override
    public List<StudentEnrollCourseDTO> getAllEnrollments() {
        return studentEnrollCourseRepository.findAll().stream()
                .map(enrollCourse -> {
                    StudentEnrollCourseDTO dto = objectMapper.convertValue(enrollCourse, StudentEnrollCourseDTO.class);

                    if (enrollCourse.getCourse() != null) {
                        dto.setCourseId(enrollCourse.getCourse().getId());
                    }
                    if (enrollCourse.getStudent() != null) {
                        dto.setStudentId(enrollCourse.getStudent().getId());
                    }
                    return dto;
                })
                .toList();
    }
}