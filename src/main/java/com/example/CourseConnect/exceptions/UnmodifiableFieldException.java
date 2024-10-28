package com.example.CourseConnect.exceptions;

public class UnmodifiableFieldException extends RuntimeException {

    public UnmodifiableFieldException(String message) {
        super(message);
    }
}