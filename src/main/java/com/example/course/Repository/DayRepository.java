package com.example.course.Repository;

import com.example.course.Dao.Day;
import com.example.course.Dto.DayDto;
import com.example.course.Helper.Constants.AppResponseMassage;
import com.example.course.Mapping.DayMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DayRepository {

    @Autowired
    private Connection connection;

    @Autowired
    private Statement statement;

    public List<Day> getDay() {
        String query = "Select * from day";

        try {
            ResultSet resultSet = statement.executeQuery(query);
            List<Day> days = new ArrayList<>();
            while (resultSet.next()) {
                Day day = new Day();
                day.setId(resultSet.getInt(1));
                day.setName(resultSet.getString(2));
                days.add(day);
            }
            return days;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Day addDay(DayDto dayDto) {
        String query = "insert into day( name) values (?)";
        Day day = new Day();
        try {
           CallableStatement callableStatement = connection.prepareCall(query);
           callableStatement.setString(1,dayDto.getName());
           callableStatement.executeUpdate();
           String q = "select * from day where name = ?";
           PreparedStatement preparedStatement = connection.prepareStatement(q);
           preparedStatement.setString(1,dayDto.getName());
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               day.setId(resultSet.getInt(1));
               day.setName(resultSet.getString(2));
           }
        } catch (SQLException e) {
           throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
        return day;
    }

    public Day deleteDay(Integer id) {
        String query = " delete from day where id = ?";
        String q = "select * from day where id = ?";
        try {
            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,id);
            ResultSet resultSet = callableStatement.executeQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            Integer son = preparedStatement.executeUpdate();
            Day day = new Day();
            while (resultSet.next()){
                day.setId(resultSet.getInt(1));
                day.setName(resultSet.getString(2));
            }
            return day;
        } catch (SQLException e) {
            throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }

    public Day updateDay(DayDto dayDto) {
        String query = "select * from day where id = ?";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,dayDto.getId());
            ResultSet resultSet = callableStatement.executeQuery();
            String q = "UPDATE day set name = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1,dayDto.getName());
            preparedStatement.setInt(2,dayDto.getId());
            Integer son = preparedStatement.executeUpdate();
            Day day = new Day();
            while (resultSet.next()){
                day.setId(resultSet.getInt(1));
                day.setName(resultSet.getString(2));
            }
            return day;
        } catch (SQLException e) {
           throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }
}
