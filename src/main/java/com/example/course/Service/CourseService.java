package com.example.course.Service;

import com.example.course.Dao.Course;
import com.example.course.Dto.CourseDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Dto.ValidatorDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Helper.Constants.AppResponseMassage;
import com.example.course.Helper.Validator;
import com.example.course.Mapping.CourseMapping;
import com.example.course.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public ResponseDto<List<CourseDto>> getCourse() {
        List<Course> courses = courseRepository.getCourse();
        if(courses.size() > 0){
            List<CourseDto> courseDtos = courses
                    .stream()
                    .map(CourseMapping::toDto)
                    .collect(Collectors.toList());
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK,courseDtos);
        }
        return new ResponseDto<>(
                false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR
        );
    }

    public ResponseDto<CourseDto> addCourse(CourseDto courseDto) {
        List<ValidatorDto> errors = Validator.addCourse(courseDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,courseDto,errors);
        }
        Course course = courseRepository.addCourse(courseDto);
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,CourseMapping.toDto(course));
    }

    public ResponseDto<CourseDto> updateCourse(CourseDto courseDto) {
        if(courseDto.getId() == null)
            return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND);
        List<ValidatorDto> errors = Validator.addCourse(courseDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,courseDto,errors);
        }
        Course course = courseRepository.updateCourse(courseDto);
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,CourseMapping.toDto(course));
    }

    public ResponseDto<CourseDto> deleteCourse(Integer id) {
        if(id > 0) {
            Course course = courseRepository.deleteCourse(id);
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,CourseMapping.toDto(course));
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.MINUS_VALUE);
    }
}
