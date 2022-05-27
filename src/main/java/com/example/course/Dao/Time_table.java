package com.example.course.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time_table {

    private Integer id;

    private Day day;

    private String start_time;

    private String end_time;
}
