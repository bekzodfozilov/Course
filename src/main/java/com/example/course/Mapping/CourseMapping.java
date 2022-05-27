package com.example.course.Mapping;

import com.example.course.Dao.Course;
import com.example.course.Dto.CourseDto;

public class CourseMapping {

    public static CourseDto toDto(Course course){
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getPrice(),
                course.getDuration()
        );
    }
}
