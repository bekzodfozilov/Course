package com.example.course.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group_time_table {

    private Time_table time_table;

    private Groups groups;

}
