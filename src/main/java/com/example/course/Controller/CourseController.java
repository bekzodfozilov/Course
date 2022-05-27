package com.example.course.Controller;

import com.example.course.Dto.CourseDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    /* get all yani barcha courselarni korish*/
    @GetMapping("get-all")
    public ResponseDto<List<CourseDto>> getCourse(){
        return courseService.getCourse();
    }
    /* add course yangi course qoshish*/
    @PostMapping("add-course")
    public ResponseDto<CourseDto> addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }
    /* update course course malumotlarini yangilash */
    @PutMapping("update-course")
    public ResponseDto<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        return courseService.updateCourse(courseDto);
    }

    /* delede course course malumotlarini ochir*/
    @DeleteMapping("delete-course")
    public ResponseDto<CourseDto> deleteCourse(@RequestParam Integer id){
        return courseService.deleteCourse(id);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<?> error(IllegalArgumentException e) {
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR ,e.getMessage());
    }
}
