package com.example.course.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private Integer id;

    private String full_name;

    private String phone;

    private Integer salary;

}
