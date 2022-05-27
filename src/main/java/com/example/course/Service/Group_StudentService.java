package com.example.course.Service;

import com.example.course.Dao.Group_student;
import com.example.course.Dto.Group_studentDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Dto.ValidatorDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Helper.Constants.AppResponseMassage;
import com.example.course.Helper.Validator;
import com.example.course.Mapping.Group_StudentMapping;
import com.example.course.Repository.Group_StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Group_StudentService {

    private final Group_StudentRepository group_studentRepository;

    public ResponseDto<List<Group_studentDto>> getGroupStudent() {
        List<Group_student> group_students = group_studentRepository.getGroupStudent();
        if(group_students.size() > 0){
            List<Group_studentDto> group_studentDtos = group_students
                    .stream()
                    .map(Group_StudentMapping::toDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,group_studentDtos);
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR);

    }


    public ResponseDto<List<Group_studentDto>> addGroupStudent(Group_studentDto group_studentDto) {
        List<ValidatorDto> errors = Validator.addGroupStudent(group_studentDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,new ArrayList<>(),errors);
        }
        List<Group_student> group_student = group_studentRepository.addGroupStudent(group_studentDto);
        if(group_student.size() > 0){
            List<Group_studentDto> group_studentDtos = group_student
                    .stream()
                    .map(Group_StudentMapping::toDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,group_studentDtos);
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,new ArrayList<>());

    }

    public ResponseDto<List<Group_studentDto>> getAllGroupStudent() {
        List<Group_student> group_students = group_studentRepository.getAllGroupStudent();
        if(group_students.size() > 0){
            List<Group_studentDto> group_studentDtos = group_students
                    .stream()
                    .map(Group_StudentMapping::toDtos)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,group_studentDtos);
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR);
    }
}
