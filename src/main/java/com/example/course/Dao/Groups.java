package com.example.course.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

    private Integer id;

    private String name;

    private Course course;

    private Teacher teacher;

    private Room room;

    private Date start_date;

    private Date end_date;

    private Status status;

    public Groups(String name) {
        this.name = name;
    }
}
