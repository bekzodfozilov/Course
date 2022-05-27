package com.example.course.Controller;

import com.example.course.Dto.Group_time_tableDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Service.Group_Time_TableControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("group_time_table")
public class Group_Time_TableController {

    private final Group_Time_TableControllerService group_time_tableControllerService;

    /* BARCHA JOIN BOLGAN DAO LAR BILAN GET QILADI*/
    @GetMapping("get-all")
    public ResponseDto<List<Group_time_tableDto>> getGroupTimeTable(){
        return group_time_tableControllerService.getGroupTimeTable();
    }
    /* ADD GROUP_TIME_TABLE QOSHISHDAN OLDIN TIME_TABLE VA GROUP TABLEDAN TEKSHIRB BOR BOLSA QOSHADI*/
    @PostMapping("add")
    public ResponseDto<List<Group_time_tableDto>> addGroupTimeTable(@RequestBody Group_time_tableDto group_time_tableDto){
        return group_time_tableControllerService.addGroupTimeTable(group_time_tableDto);
    }

   /*Update va delete methodlari bomidi chunki ozini id yoq*/

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<?> error(IllegalArgumentException e) {
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR ,e.getMessage());
    }

}
