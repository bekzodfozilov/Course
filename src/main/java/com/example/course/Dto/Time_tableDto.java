package com.example.course.Dto;

import com.example.course.Dao.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time_tableDto {

    private Integer id;

    private DayDto dayDto;

    private String start_time;

    private String end_time;


}
