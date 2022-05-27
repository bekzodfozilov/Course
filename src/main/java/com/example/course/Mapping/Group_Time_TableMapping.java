package com.example.course.Mapping;

import com.example.course.Dao.Group_time_table;
import com.example.course.Dto.Group_time_tableDto;
import com.example.course.Dto.GroupsDto;
import com.example.course.Dto.Time_tableDto;



public class Group_Time_TableMapping {

    public static Group_time_tableDto toDto(Group_time_table group_time_table) {
        Time_tableDto time_tableDto = Time_TableMapping.toDto(group_time_table.getTime_table());
        GroupsDto groupsDto = GroupsMapping.toDto(group_time_table.getGroups());
        return new Group_time_tableDto(time_tableDto,groupsDto);

    }
}
