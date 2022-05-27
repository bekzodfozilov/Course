package com.example.course.Service;

import com.example.course.Dao.Group_time_table;
import com.example.course.Dto.Group_time_tableDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Dto.ValidatorDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Helper.Constants.AppResponseMassage;
import com.example.course.Helper.Validator;
import com.example.course.Mapping.Group_Time_TableMapping;
import com.example.course.Repository.Group_Time_TableControllerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Group_Time_TableControllerService {

    private final Group_Time_TableControllerRepository group_time_tableControllerRepository;
    public ResponseDto<List<Group_time_tableDto>> getGroupTimeTable() {
        List<Group_time_table> group_time_tables = group_time_tableControllerRepository.getGroupTimeTable();
        if (group_time_tables.size() > 0) {
            List<Group_time_tableDto> group_time_tableDtos = group_time_tables
                    .stream()
                    .map(Group_Time_TableMapping::toDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, group_time_tableDtos);
        }
        return new ResponseDto<>(
                false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR
        );
    }

    public ResponseDto<List<Group_time_tableDto>> addGroupTimeTable(Group_time_tableDto group_time_tableDto) {
        List<ValidatorDto> errors = Validator.addGroupTImeTable(group_time_tableDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,new ArrayList<>(),errors);

        }
        List<Group_time_table> group_time_tables = group_time_tableControllerRepository.addGroupTimeTable(group_time_tableDto);
        if(group_time_tables.size() > 0){
            List<Group_time_tableDto> groupTimeTableDto = group_time_tables
                    .stream()
                    .map(Group_Time_TableMapping::toDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,groupTimeTableDto);
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND);
    }
}
