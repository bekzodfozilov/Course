package com.example.course.Mapping;

import com.example.course.Dao.Groups;
import com.example.course.Dao.Status;
import com.example.course.Dto.*;

public class GroupsMapping {
    public static GroupsDto toDto(Groups groups) {
        CourseDto courseDto = CourseMapping.toDto(groups.getCourse());
        TeacherDto teacherDto = TeacherMapping.ToDto(groups.getTeacher());
        RoomDto roomDto = RoomMapping.toDto(groups.getRoom());
        StatusDto statusDto = StatusMapping.toDto(groups.getStatus());
        return new GroupsDto(
                groups.getId(),
                groups.getName(),
                courseDto,
                teacherDto,
                roomDto,
                groups.getStart_date(),
                groups.getEnd_date(),
                statusDto
        );
    }
    public static GroupsDto toName(Groups groups){
        return new GroupsDto(groups.getName());
    }
}
