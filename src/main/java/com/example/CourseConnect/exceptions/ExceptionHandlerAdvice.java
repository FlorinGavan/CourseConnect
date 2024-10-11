package com.example.CourseConnect.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final ObjectMapper objectMapper;

    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(StudentCreateException.class)
    public ResponseEntity<String> studentCreateException(StudentCreateException studentCreateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", studentCreateException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(CourseCreateException.class)
    public ResponseEntity<String> courseCreateException(CourseCreateException courseCreateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", courseCreateException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(TeacherCreateException.class)
    public ResponseEntity<String> teacherCreateException(TeacherCreateException teacherCreateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", teacherCreateException.getMessage())), BAD_REQUEST);
    }

    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing response to string");
        }
        return "Internal error";
    }
}