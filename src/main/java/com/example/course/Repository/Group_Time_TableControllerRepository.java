package com.example.course.Repository;

import com.example.course.Dao.*;
import com.example.course.Dto.Group_time_tableDto;
import com.example.course.Helper.Constants.AppResponseMassage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class Group_Time_TableControllerRepository {

    private final Connection connection;

    private final Statement statement;

    public List<Group_time_table> getGroupTimeTable() {
        String query = "select tt.id , day.id , day.name , tt.start_time , tt.end_time , g.id , g.name , c.id , c.name , c.price , c.duration,\n" +
                "t.id , t.full_name , t.phone , t.salary , r.id, r.name , r.capacity , g.start_date , g.end_date , s.id , s.name , s.description\n" +
                "from group_time_table\n" +
                "join time_table tt on group_time_table.time_table_id = tt.id join day on tt.day_id = day.id\n" +
                "join groups g on group_time_table.gruop_id = g.id\n" +
                "join course c on g.course_id = c.id join teacher t on g.teacher_id = t.id join room r on g.room_id = r.id\n" +
                "join status s on g.status_id = s.id";

        try {
            ResultSet resultSet = statement.executeQuery(query);
            List<Group_time_table> group_time_tables = new ArrayList<>();
            while (resultSet.next()){
                Time_table time_table = new Time_table();
                time_table.setId(resultSet.getInt(1));
                Day day = new Day();
                day.setId(resultSet.getInt(2));
                day.setName(resultSet.getString(3));
                time_table.setDay(day);
                time_table.setStart_time(resultSet.getString(4));
                time_table.setEnd_time(resultSet.getString(5));
                Groups groups = new Groups();
                groups.setId(resultSet.getInt(6));
                groups.setName(resultSet.getString(7));
                Course course = new Course();
                course.setId(resultSet.getInt(8));
                course.setName(resultSet.getString(9));
                course.setPrice(resultSet.getInt(10));
                course.setDuration(resultSet.getString(11));
                groups.setCourse(course);
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt(12));
                teacher.setFull_name(resultSet.getString(13));
                teacher.setPhone(resultSet.getString(14));
                teacher.setSalary(resultSet.getInt(15));
                groups.setTeacher(teacher);
                Room room = new Room();
                room.setId(resultSet.getInt(16));
                room.setName(resultSet.getString(17));
                room.setCapacity(resultSet.getInt(18));
                groups.setRoom(room);
                groups.setStart_date(resultSet.getDate(19));
                groups.setEnd_date(resultSet.getDate(20));
                Status status = new Status();
                status.setId(resultSet.getInt(21));
                status.setName(resultSet.getString(22));
                status.setDescription(resultSet.getString(23));
                groups.setStatus(status);
                Group_time_table group_time_table = new Group_time_table(time_table,groups);
                group_time_tables.add(group_time_table);
            }
            return group_time_tables;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Group_time_table> addGroupTimeTable(Group_time_tableDto group_time_tableDto) {
        String s = "select id from time_table where id = ?";
        String s1 = "select id from groups where id = ?";
        try {
            CallableStatement callableStatement = connection.prepareCall(s);
            callableStatement.setInt(1,group_time_tableDto.getTime_tableDto().getId());
            ResultSet resultSet = callableStatement.executeQuery();
            CallableStatement callableStatement1 = connection.prepareCall(s1);
            callableStatement1.setInt(1,group_time_tableDto.getGroupsDto().getId());
            ResultSet resultSet1 = callableStatement1.executeQuery();
            Time_table time_table = new Time_table();
            while (resultSet.next()){
                time_table.setId(resultSet.getInt("id"));
            }
            Groups groups = new Groups();
            while (resultSet1.next()){
                groups.setId(resultSet1.getInt("id"));
            }
            if(group_time_tableDto.getGroupsDto().getId() == groups.getId() && group_time_tableDto.getTime_tableDto().getId() == time_table.getId()) {
                String query = "insert into group_time_table(time_table_id, gruop_id) VALUES (?,?)";
                CallableStatement callableStatement2 = connection.prepareCall(query);
                callableStatement2.setInt(1, group_time_tableDto.getTime_tableDto().getId());
                callableStatement2.setInt(2, group_time_tableDto.getGroupsDto().getId());
                callableStatement2.executeUpdate();

                String query1 = "select tt.id , day.id , day.name , tt.start_time , tt.end_time , g.id , g.name , c.id , c.name , c.price , c.duration,\n" +
                        "t.id , t.full_name , t.phone , t.salary , r.id, r.name , r.capacity , g.start_date , g.end_date , s.id , s.name , s.description\n" +
                        "from group_time_table\n" +
                        "join time_table tt on group_time_table.time_table_id = tt.id join day on tt.day_id = day.id\n" +
                        "join groups g on group_time_table.gruop_id = g.id\n" +
                        "join course c on g.course_id = c.id join teacher t on g.teacher_id = t.id join room r on g.room_id = r.id\n" +
                        "join status s on g.status_id = s.id";
                ResultSet resultSet2 = statement.executeQuery(query1);
                List<Group_time_table> group_time_tables = new ArrayList<>();
                while (resultSet2.next()) {
                    Time_table time_table1 = new Time_table();
                    time_table1.setId(resultSet2.getInt(1));
                    Day day = new Day();
                    day.setId(resultSet2.getInt(2));
                    day.setName(resultSet2.getString(3));
                    time_table1.setDay(day);
                    time_table1.setStart_time(resultSet2.getString(4));
                    time_table1.setEnd_time(resultSet2.getString(5));
                    Groups groups1 = new Groups();
                    groups1.setId(resultSet2.getInt(6));
                    groups1.setName(resultSet2.getString(7));
                    Course course = new Course();
                    course.setId(resultSet2.getInt(8));
                    course.setName(resultSet2.getString(9));
                    course.setPrice(resultSet2.getInt(10));
                    course.setDuration(resultSet2.getString(11));
                    groups1.setCourse(course);
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet2.getInt(12));
                    teacher.setFull_name(resultSet2.getString(13));
                    teacher.setPhone(resultSet2.getString(14));
                    teacher.setSalary(resultSet2.getInt(15));
                    groups1.setTeacher(teacher);
                    Room room = new Room();
                    room.setId(resultSet2.getInt(16));
                    room.setName(resultSet2.getString(17));
                    room.setCapacity(resultSet2.getInt(18));
                    groups1.setRoom(room);
                    groups1.setStart_date(resultSet2.getDate(19));
                    groups1.setEnd_date(resultSet2.getDate(20));
                    Status status = new Status();
                    status.setId(resultSet2.getInt(21));
                    status.setName(resultSet2.getString(22));
                    status.setDescription(resultSet2.getString(23));
                    groups1.setStatus(status);
                    Group_time_table group_time_table = new Group_time_table(time_table1, groups1);
                    group_time_tables.add(group_time_table);
                }
                return group_time_tables;
            }
            throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);

        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }
}
