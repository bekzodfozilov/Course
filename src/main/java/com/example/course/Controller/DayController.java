package com.example.course.Controller;

import com.example.course.Dto.DayDto;
import com.example.course.Dto.ResponseDto;
import com.example.course.Helper.Constants.AppResponseCode;
import com.example.course.Service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService ;

    /* get yani barhca kunlarni korish uchun*/
    @GetMapping("get-all")
    public ResponseDto<List<DayDto>> getDay(){
        return dayService.getDay();
    }
    /* add kun qoshish */
    @PostMapping("add")
    public ResponseDto<DayDto> addDay(@RequestBody DayDto dayDto){
        return dayService.addDay(dayDto);
    }
    /* delete yani malumot o'chirish */
    @DeleteMapping("delete")
    public ResponseDto<DayDto> deleteDay(@RequestParam Integer id)  {
        return dayService.delateDay(id);
    }

    /* update malumotlarni yangilash uchun*/
    @PutMapping("update")
    public ResponseDto<DayDto> updateDay(@RequestBody DayDto dayDto){
        return dayService.updateDay(dayDto);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<?> error(IllegalArgumentException e) {
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR ,e.getMessage());
    }
}
