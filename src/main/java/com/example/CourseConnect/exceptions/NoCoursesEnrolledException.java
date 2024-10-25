package com.example.CourseConnect.exceptions;

public class NoCoursesEnrolledException  extends RuntimeException{
     public NoCoursesEnrolledException(String message) {
        super(message);
    }
}