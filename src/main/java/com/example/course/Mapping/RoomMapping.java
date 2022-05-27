package com.example.course.Mapping;

import com.example.course.Dao.Room;
import com.example.course.Dto.RoomDto;

public class RoomMapping {
    public static RoomDto toDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getCapacity()
        );
    }
}
