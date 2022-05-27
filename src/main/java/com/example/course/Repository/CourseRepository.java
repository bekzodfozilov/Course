package com.example.course.Repository;

import com.example.course.Dao.Course;
import com.example.course.Dto.CourseDto;
import com.example.course.Helper.Constants.AppResponseMassage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepository {


    private final Connection connection;

    private final Statement statement;



    public List<Course> getCourse() {
        String query = "select * from course";
        try {
            List<Course> courses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setName(resultSet.getString(2));
                course.setPrice(resultSet.getInt(3));
                course.setDuration(resultSet.getString(4));
            courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }

    public Course addCourse(CourseDto courseDto) {
        String query = "insert into course(name, price, duration) values (?,?,?)";

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1,courseDto.getName());
            callableStatement.setInt(2,courseDto.getPrice());
            callableStatement.setString(3,courseDto.getDuration());
            callableStatement.executeUpdate();
            String q = "select * from course where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1,courseDto.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            Course course = new Course();
            while (resultSet.next()){
                course.setId(resultSet.getInt(1));
                course.setName(resultSet.getString(2));
                course.setPrice(resultSet.getInt(3));
                course.setDuration(resultSet.getString(4));
            }
            return course;
        } catch (SQLException e) {
         throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }

    public Course updateCourse(CourseDto courseDto) {
        String query = "select * from course where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,courseDto.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            String q = "update course set name = ? , price = ? , duration = ? where id = ?";
            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setString(1,courseDto.getName());
            callableStatement.setInt(2,courseDto.getPrice());
            callableStatement.setString(3, courseDto.getDuration());
            callableStatement.setInt(4,courseDto.getId());
            callableStatement.executeUpdate();
            Course course = new Course();
            while (resultSet.next()){
                course.setId(resultSet.getInt(1));
                course.setName(resultSet.getString(2));
                course.setPrice(resultSet.getInt(3));
                course.setDuration(resultSet.getString(4));
            }
            return course;
        } catch (SQLException e) {
          throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }

    public Course deleteCourse(Integer id) {
        String query = "select * from course where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String q = "delete from course where id = ?";
            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,id);
            callableStatement.executeUpdate();
            Course course = new Course();
            while (resultSet.next()){
                course.setId(resultSet.getInt(1));
                course.setName(resultSet.getString(2));
                course.setPrice(resultSet.getInt(3));
                course.setDuration(resultSet.getString(4));
            }
            return course;
        } catch (SQLException e) {
         throw new RuntimeException(AppResponseMassage.DATABASE_ERROR);
        }
    }
}
