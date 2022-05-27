package com.example.course.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupsDto {

    private Integer id;

    private String name;

    private CourseDto courseDto;

    private TeacherDto teacherDto;

    private RoomDto roomDto;

    private Date start_date;

    private Date end_date;

    private StatusDto StatusDto ;

    public GroupsDto(String name) {
        this.name = name;
    }


}
