package com.example.course.Mapping;

import com.example.course.Dao.Time_table;
import com.example.course.Dto.DayDto;
import com.example.course.Dto.Time_tableDto;

public class Time_TableMapping {
    public static Time_tableDto toDto(Time_table time_table) {
        DayDto dayDto = DayMapping.ToDto(time_table.getDay());
        return new Time_tableDto(
                time_table.getId(),
                dayDto,
                time_table.getStart_time(),
                time_table.getEnd_time()

        );
    }
}
