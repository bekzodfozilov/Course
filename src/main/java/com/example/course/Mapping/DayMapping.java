package com.example.course.Mapping;

import com.example.course.Dao.Day;
import com.example.course.Dto.DayDto;

public class DayMapping {

    public static DayDto ToDto(Day day){
        return new DayDto(
                day.getId(),
                day.getName()
        );
    }

    public static Day ToEntity(DayDto dayDto) {
        return new Day(
                dayDto.getId(),
                dayDto.getName()
        );
    }
}
