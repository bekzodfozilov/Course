package com.example.course.Mapping;

import com.example.course.Dao.Student;
import com.example.course.Dto.StudentDto;

public class StudentMapping {
    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFull_name(),
                student.getPhone()
        );
    }
}
