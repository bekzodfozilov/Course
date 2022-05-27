package com.example.course.Controller;

import com.example.course.Dto.Group_studentDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Service.Group_StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group-studen")
@RequiredArgsConstructor
public class Group_StudentController {

    private final Group_StudentService group_studentService;

    /* Barcha group_studentlarni gruh noma va studentlari bilan get qiladi*/
    @GetMapping()
    public ResponseDto<List<Group_studentDto>> getGroupStudent()throws RuntimeException{
        return group_studentService.getGroupStudent();
    }
    /* add group_student barhca fildlari boyicha ketshirib keyin add qiladi*/
    @PostMapping
    public ResponseDto<List<Group_studentDto>> addGroupStudent(@RequestBody Group_studentDto group_studentDto) throws RuntimeException{
        return group_studentService.addGroupStudent(group_studentDto);
    }

    /* barcha fildi boyicha get qiladi yani join qib*/
    @GetMapping("get-all")
    public ResponseDto<List<Group_studentDto>> getAllGroupStudent(){
        return group_studentService.getAllGroupStudent();
    }

    /*bunda delete yoki update bomidi chunki ozini idisi yoq */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<?> error(IllegalArgumentException e) {
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR ,e.getMessage());
    }
}
