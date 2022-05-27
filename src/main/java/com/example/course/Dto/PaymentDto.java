package com.example.course.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Integer id;

    private Pay_typeDto pay_typeDto;

    private Double sum;

    private String description;

    private StudentDto studentdto;

    private Date created_date;
}
