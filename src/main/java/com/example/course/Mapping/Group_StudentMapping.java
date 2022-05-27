package com.example.course.Mapping;


import com.example.course.Dao.Group_student;

import com.example.course.Dto.Group_studentDto;
import com.example.course.Dto.GroupsDto;
import com.example.course.Dto.StudentDto;

public class Group_StudentMapping {
    public static Group_studentDto toDto(Group_student group_student) {
        if(group_student == null || group_student.getStudent() == null || group_student.getGroups() == null)
            return null;
        GroupsDto groupsDto = GroupsMapping.toName(group_student.getGroups());
        StudentDto studentDto = StudentMapping.toDto(group_student.getStudent());
        return new Group_studentDto(
             groupsDto,
                studentDto
        );
    }
    public static Group_studentDto toDtos(Group_student group_student) {
        if(group_student == null || group_student.getStudent() == null || group_student.getGroups() == null)
            return null;
        GroupsDto groupsDto = GroupsMapping.toDto(group_student.getGroups());
        StudentDto studentDto = StudentMapping.toDto(group_student.getStudent());
        return new Group_studentDto(
                groupsDto,
                studentDto
        );
    }

}
