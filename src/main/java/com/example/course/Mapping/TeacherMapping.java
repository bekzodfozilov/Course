package com.example.course.Mapping;

import com.example.course.Dao.Teacher;
import com.example.course.Dto.TeacherDto;

public class TeacherMapping {
    public static TeacherDto ToDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFull_name(),
                teacher.getPhone(),
                teacher.getSalary()
        );
    }
}
