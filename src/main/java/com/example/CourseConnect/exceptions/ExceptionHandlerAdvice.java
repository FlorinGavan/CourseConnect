package com.example.CourseConnect.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.*;

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

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> StudentNotFoundException(StudentNotFoundException studentNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", studentNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(CourseCreateException.class)
    public ResponseEntity<String> courseCreateException(CourseCreateException courseCreateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", courseCreateException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> courseNotFoundException(CourseNotFoundException courseNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", courseNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(CourseRoomSizeException.class)
    public  ResponseEntity<String>courseRoomSizeException ( CourseRoomSizeException courseRoomSizeException){
        return  new ResponseEntity<>(objectToString(Map.of("message", courseRoomSizeException.getMessage())), CONFLICT);
    }

    @ExceptionHandler(TeacherCreateException.class)
    public ResponseEntity<String> teacherCreateException(TeacherCreateException teacherCreateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", teacherCreateException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<String> teacherNotFoundException(TeacherNotFoundException teacherNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", teacherNotFoundException.getMessage())), NOT_FOUND);
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