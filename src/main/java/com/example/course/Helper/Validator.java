package com.example.course.Helper;

import com.example.course.Dto.*;
import com.example.course.Helper.Constants.AppResponseMassage;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<ValidatorDto> addDay(DayDto dayDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(dayDto.getName() == null || dayDto.getName().trim().length() < 1){
            errors.add(new ValidatorDto("name", AppResponseMassage.NOT_FOUND));
        }
        return errors;
    }

    public static List<ValidatorDto> addCourse(CourseDto courseDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(courseDto.getName() == null || courseDto.getName().trim().length() < 1){
            errors.add(new ValidatorDto("course",AppResponseMassage.NOT_FOUND));
        }
        if(courseDto.getPrice() == null){
            errors.add(new ValidatorDto("price",AppResponseMassage.NOT_FOUND));
        }else if(courseDto.getPrice() < 0){
            errors.add(new ValidatorDto("price",AppResponseMassage.MINUS_VALUE));
        }
        if (courseDto.getDuration() == null || courseDto.getDuration().trim().length() < 1){
            errors.add(new ValidatorDto("duration",AppResponseMassage.NOT_FOUND));
        }
        return errors;
    }


    public static List<ValidatorDto> addGroupStudent(Group_studentDto group_studentDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(group_studentDto.getGroupsDto().getId() == null){
            errors.add(new ValidatorDto("id",AppResponseMassage.NOT_FOUND));
        }else if(group_studentDto.getGroupsDto().getId() < 0){
            errors.add(new ValidatorDto("id",AppResponseMassage.MINUS_VALUE));
        }
        if(group_studentDto.getStudentDto().getId() == null){
            errors.add(new ValidatorDto("id",AppResponseMassage.NOT_FOUND));
        }else if(group_studentDto.getStudentDto().getId() < 0){
            errors.add(new ValidatorDto("id",AppResponseMassage.MINUS_VALUE));
        }
        return errors;
    }

    public static List<ValidatorDto> addGroupTImeTable(Group_time_tableDto group_time_tableDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(group_time_tableDto.getTime_tableDto().getId() == null){
            errors.add(new ValidatorDto("TIme_table_id",AppResponseMassage.NOT_FOUND));
        }else if(group_time_tableDto.getTime_tableDto().getId() < 1){
            errors.add(new ValidatorDto("Time_table_id",AppResponseMassage.MINUS_VALUE));
        }
        if(group_time_tableDto.getGroupsDto().getId() == null){
            errors.add(new ValidatorDto("Group_id",AppResponseMassage.NOT_FOUND));
        }else if (group_time_tableDto.getGroupsDto().getId() < 1){
            errors.add(new ValidatorDto("Group_id",AppResponseMassage.MINUS_VALUE));
        }
        return errors;
    }
}
