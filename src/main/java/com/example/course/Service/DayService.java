package com.example.course.Service;

import com.example.course.Dao.Day;
import com.example.course.Dto.DayDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Dto.ValidatorDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Helper.Constants.AppResponseMassage;
import com.example.course.Helper.Validator;
import com.example.course.Mapping.DayMapping;
import com.example.course.Repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    public ResponseDto<List<DayDto>> getDay() {
        List<Day> days = dayRepository.getDay();
        if (days.size() > 0) {
            List<DayDto> dayDtos = days
                    .stream()
                    .map(DayMapping::ToDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, dayDtos);
        }
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR);

    }

    public ResponseDto<DayDto> addDay(DayDto dayDto) {
        List<ValidatorDto> errors = Validator.addDay(dayDto);
        if (errors.size() > 0) {
            return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, dayDto, errors);
        }
        Day day = dayRepository.addDay(dayDto);
        return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, DayMapping.ToDto(day));

    }


    public ResponseDto<DayDto> delateDay(Integer id) {
        Day day = dayRepository.deleteDay(id);
        return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, DayMapping.ToDto(day));

    }

    public ResponseDto<DayDto> updateDay(DayDto dayDto) {
        List<ValidatorDto> errors = Validator.addDay(dayDto);
        if (errors.size() > 0) {
            return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, dayDto, errors);
        }
        Day day = dayRepository.updateDay(dayDto);
        return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, DayMapping.ToDto(day));
    }
}
