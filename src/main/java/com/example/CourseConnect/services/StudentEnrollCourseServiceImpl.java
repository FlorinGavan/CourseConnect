package com.example.CourseConnect.services;

import com.example.CourseConnect.exceptions.CourseNotFoundException;
import com.example.CourseConnect.exceptions.CourseRoomSizeException;
import com.example.CourseConnect.exceptions.StudentNotFoundException;
import com.example.CourseConnect.models.dtos.StudentEnrollCourseDTO;
import com.example.CourseConnect.models.entities.Course;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.models.entities.StudentEnrollCourse;
import com.example.CourseConnect.repositories.CourseRepository;
import com.example.CourseConnect.repositories.StudentEnrollCourseRepository;
import com.example.CourseConnect.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentEnrollCourseServiceImpl implements StudentEnrollCourseService {

    private final StudentEnrollCourseRepository studentEnrollCourseRepository;
    private  final StudentRepository studentRepository;
    private  final CourseRepository courseRepository;

    public StudentEnrollCourseServiceImpl(StudentEnrollCourseRepository studentEnrollCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentEnrollCourseRepository = studentEnrollCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void enrollCourse(StudentEnrollCourseDTO studentEnrollCourseDTO) {
        Course course = courseRepository.findById(studentEnrollCourseDTO.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (course.getCourseRoomSize() <= 0){
            throw new CourseRoomSizeException("Course room is full !");
        }
        Student student = studentRepository.findById(studentEnrollCourseDTO.getStudentId())
                .orElseThrow( () -> new StudentNotFoundException("Student not found"));
        StudentEnrollCourse studentEnrollCourse = new StudentEnrollCourse();
        studentEnrollCourse.setCourse(course);
        studentEnrollCourse.setStudent(student);

        course.setCourseRoomSize(course.getCourseRoomSize() -1);
        courseRepository.save(course);

        studentEnrollCourseRepository.save(studentEnrollCourse);
        log.info("Student has enrolled successful on this course");
    }
}