package com.example.course.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group_studentDto {


    private GroupsDto groupsDto;

    private StudentDto studentDto;


}
