package com.example.CourseConnect.unit_test;

import com.example.CourseConnect.exceptions.StudentCreateException;
import com.example.CourseConnect.exceptions.StudentNotFoundException;
import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;
import com.example.CourseConnect.models.entities.Student;
import com.example.CourseConnect.repositories.StudentRepository;
import com.example.CourseConnect.services.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test creating a new student successfully")
    void testCreateStudentSuccess() {
        RequestStudentDTO requestStudentDTO = new RequestStudentDTO();
        requestStudentDTO.setFirstName("John");
        requestStudentDTO.setLastName("Doe");
        requestStudentDTO.setEmail("john.doe@example.com");

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        ResponseStudentDTO expectedResponse = new ResponseStudentDTO(1L, "John", "Doe", "john.doe@example.com");

        when(studentRepository.findStudentByEmail(requestStudentDTO.getEmail())).thenReturn(Optional.empty());
        when(objectMapper.convertValue(requestStudentDTO, Student.class)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(objectMapper.convertValue(student, ResponseStudentDTO.class)).thenReturn(expectedResponse);

        ResponseStudentDTO response = studentService.createStudent(requestStudentDTO);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(studentRepository).save(student);
    }

    @Test
    @DisplayName("Test creating a student with an existing email throws StudentCreateException")
    void testCreateStudentEmailExists() {
        RequestStudentDTO requestStudentDTO = new RequestStudentDTO();
        requestStudentDTO.setEmail("john.doe@example.com");

        when(studentRepository.findStudentByEmail(requestStudentDTO.getEmail()))
                .thenReturn(Optional.of(new Student()));

        assertThrows(StudentCreateException.class, () -> studentService.createStudent(requestStudentDTO));
        verify(studentRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test retrieving all students successfully")
    void testGetAllStudentsSuccess() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        ResponseStudentDTO studentDTO = new ResponseStudentDTO(1L, "John", "Doe", "john.doe@example.com");

        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(objectMapper.convertValue(student, ResponseStudentDTO.class)).thenReturn(studentDTO);

        List<ResponseStudentDTO> students = studentService.getAllStudents();

        assertEquals(1, students.size());
        assertEquals(studentDTO, students.get(0));
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("Test retrieving a student by ID successfully")
    void testGetStudentByIdSuccess() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        ResponseStudentDTO expectedResponse = new ResponseStudentDTO(studentId, "John", "Doe", "john.doe@example.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(objectMapper.convertValue(student, ResponseStudentDTO.class)).thenReturn(expectedResponse);

        ResponseStudentDTO response = studentService.getStudentById(studentId);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(studentRepository).findById(studentId);
    }

    @Test
    @DisplayName("Test retrieving a student by ID throws StudentNotFoundException when student does not exist")
    void testGetStudentByIdNotFound() {
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(studentId));
        verify(studentRepository).findById(studentId);
    }

    @Test
    @DisplayName("Test deleting a student successfully")
    void testDeleteStudentSuccess() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById(studentId);

        studentService.deleteStudent(studentId);

        verify(studentRepository).deleteById(studentId);
        verify(studentRepository).findById(studentId);
    }

    @Test
    @DisplayName("Test deleting a student throws StudentNotFoundException when student does not exist")
    void testDeleteStudentNotFound() {
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(studentId));
        verify(studentRepository, never()).deleteById(studentId);
    }
}