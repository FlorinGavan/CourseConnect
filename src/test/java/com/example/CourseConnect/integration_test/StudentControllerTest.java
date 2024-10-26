package com.example.CourseConnect.integration_test;

import com.example.CourseConnect.exceptions.StudentNotFoundException;
import com.example.CourseConnect.models.dtos.RequestStudentDTO;
import com.example.CourseConnect.models.dtos.ResponseStudentDTO;
import com.example.CourseConnect.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    @DisplayName("When creating a new Student, return status 200 with correct details")
    void testCreateStudentSuccess() throws Exception {
        RequestStudentDTO requestStudentDTO = new RequestStudentDTO();
        requestStudentDTO.setFirstName("John");
        requestStudentDTO.setLastName("Doe");
        requestStudentDTO.setEmail("john.doe@example.com");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestStudentDTO)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("GET /api/students - Success")
    void testGetAllStudentsSuccess() throws Exception {
        ResponseStudentDTO studentDTO = new ResponseStudentDTO(1L, "John", "Doe", "john.doe@example.com");
        List<ResponseStudentDTO> students = Collections.singletonList(studentDTO);

        BDDMockito.given(studentService.getAllStudents()).willReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(studentDTO.getId()))
                .andExpect(jsonPath("$[0].firstName").value(studentDTO.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(studentDTO.getLastName()))
                .andExpect(jsonPath("$[0].email").value(studentDTO.getEmail()));
    }

    @Test
    @DisplayName("GET /api/students/{id} - Success")
    void testGetStudentByIdSuccess() throws Exception {
        Long studentId = 1L;
        ResponseStudentDTO studentDTO = new ResponseStudentDTO(studentId, "John", "Doe", "john.doe@example.com");

        BDDMockito.given(studentService.getStudentById(studentId)).willReturn(studentDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentDTO.getId()))
                .andExpect(jsonPath("$.firstName").value(studentDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(studentDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(studentDTO.getEmail()));
    }

    @Test
    @DisplayName("GET /api/students/{id} - Not Found")
    void testGetStudentByIdNotFound() throws Exception {
        Long studentId = 1L;

        BDDMockito.given(studentService.getStudentById(studentId))
                .willThrow(new StudentNotFoundException("Student not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/students/{id} - Success")
    void testDeleteStudentSuccess() throws Exception {
        Long studentId = 1L;

        BDDMockito.doNothing().when(studentService).deleteStudent(studentId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted"));
    }

    @Test
    @DisplayName("DELETE /api/students/{id} - Not Found")
    void testDeleteStudentNotFound() throws Exception {
        Long studentId = 1L;

        BDDMockito.doThrow(new StudentNotFoundException("Student with id " + studentId + " not found"))
                .when(studentService).deleteStudent(studentId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}