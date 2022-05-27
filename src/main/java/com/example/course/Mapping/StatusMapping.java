package com.example.course.Mapping;

import com.example.course.Dao.Status;
import com.example.course.Dto.StatusDto;

public class StatusMapping {
    public static StatusDto toDto(Status status) {
        return new StatusDto(
                status.getId(),
                status.getName(),
                status.getDescription()
        );
    }
}
