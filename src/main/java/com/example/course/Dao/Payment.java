package com.example.course.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Integer id;

    private Integer pay_type_id;

    private Double sum;

    private String description;

    private Integer student_id;

    private Date created_date;
}
