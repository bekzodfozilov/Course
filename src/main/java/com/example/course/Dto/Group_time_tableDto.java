package com.example.course.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group_time_tableDto {


    private Time_tableDto time_tableDto;


    private GroupsDto groupsDto;
}
