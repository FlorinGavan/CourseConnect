package com.example.CourseConnect.services;

import com.example.CourseConnect.models.entities.Category;
import com.example.CourseConnect.models.entities.Course;
import org.springframework.data.jpa.domain.Specification;

import java.time.DayOfWeek;

public class CourseSpecification {

    public static Specification<Course> nameContains(String name) {
        return (course, query, criteriaBuilder) -> name == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(course.get("name")), "%" + name.toLowerCase() + "%");
    }
    public static Specification<Course> categoryContains(Category category){
        return (course , query, criteriaBuilder) -> category == null ? null :
                criteriaBuilder.like(criteriaBuilder.upper(course.get("category")), "%" + category + "%");
    }
    public   static  Specification<Course> dayOfWeekContains(DayOfWeek courseDay) {
        return (course, query, criteriaBuilder) -> courseDay == null ? null :
                criteriaBuilder.like(criteriaBuilder.upper(course.get("courseDay")), "%"+ courseDay + "%" );
    }
}