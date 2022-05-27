package com.example.course.Repository;

import com.example.course.Dao.*;
import com.example.course.Dto.Group_studentDto;
import com.example.course.Helper.Constants.AppResponseMassage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Repository
@RequiredArgsConstructor
public class Group_StudentRepository {

    private final Connection connection;

    private final Statement statement;

    public List<Group_student> getGroupStudent() {
        String query = "select groups.name , s.id , s.full_name , s.phone  from group_student join student s on s.id = group_student.student_id join groups on group_id = groups.id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            List<Group_student> group_students = new ArrayList<>();
            while (resultSet.next()){
                Groups groups = new Groups();
                Student student = new Student();
                groups.setName(resultSet.getString(1));
                student.setId(resultSet.getInt(2));
                student.setFull_name(resultSet.getString(3));
                student.setPhone(resultSet.getString(4));
                Group_student group_student = new Group_student(groups,student);
                group_students.add(group_student);
            }
            return group_students;
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    public List<Group_student> addGroupStudent(Group_studentDto group_studentDto) {
        String query = "select id from groups where id = ?";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,group_studentDto.getGroupsDto().getId());
            ResultSet resultSet = callableStatement.executeQuery();
            Groups groups = new Groups();
            while (resultSet.next()){
                groups.setId(resultSet.getInt("id"));
            }
            String query1 = "select id from student where id = ?";
            CallableStatement callableStatement1 = connection.prepareCall(query1);
            callableStatement1.setInt(1,group_studentDto.getStudentDto().getId());
            ResultSet resultSet1 = callableStatement1.executeQuery();
            Student student = new Student();
            while (resultSet1.next()){
                student.setId(resultSet1.getInt("id"));
            }
            if(groups.getId() == group_studentDto.getGroupsDto().getId() && student.getId() == group_studentDto.getStudentDto().getId()){
                String insert = "insert into group_student(group_id, student_id) values (?,?)";
                CallableStatement callableStatement2 = connection.prepareCall(insert);
                callableStatement2.setInt(1,group_studentDto.getGroupsDto().getId());
                callableStatement2.setInt(2,group_studentDto.getStudentDto().getId());
               callableStatement2.executeUpdate();
                PreparedStatement preparedStatement = connection.prepareStatement("select groups.name , s.id , s.full_name , s.phone  from group_student join student s on s.id = group_student.student_id join groups on group_id = groups.id");
               ResultSet resultSet2 = preparedStatement.executeQuery();
                List<Group_student> group_students = new ArrayList<>();
                while (resultSet2.next()){
                    Groups groups1 = new Groups();
                    Student student1 = new Student();
                    groups1.setName(resultSet2.getString(1));
                    student1.setId(resultSet2.getInt(2));
                    student1.setFull_name(resultSet2.getString(3));
                    student1.setPhone(resultSet2.getString(4));
                    Group_student group_student = new Group_student(groups1,student1);
                    group_students.add(group_student);
                }
                return group_students;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public List<Group_student> getAllGroupStudent() {
      String query = "select g.id , g.name , c.id , c.name , c.price , c.duration , t.id , t.full_name ,  t.phone,t.salary, r.id, r.name, r.capacity,\n" +
              " g.start_date , g.end_date , g.status_id,s.id, s.full_name, s.phone from group_student join student s on s.id = group_student.student_id join groups g on group_student.group_id = g.id\n" +
              "join course c on c.id = g.course_id join room r on r.id = g.room_id join teacher t on t.id = g.teacher_id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            List<Group_student> group_students = new ArrayList<>();
            while (resultSet.next()){
                Groups groups = new Groups();
                groups.setId(resultSet.getInt(1));
                groups.setName(resultSet.getString(2));
                Course course = new Course();
                course.setId(resultSet.getInt(3));
                course.setName(resultSet.getString(4));
                course.setPrice(resultSet.getInt(5));
                course.setDuration(resultSet.getString(6));
                groups.setCourse(course);
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt(7));
                teacher.setFull_name(resultSet.getString(8));
                teacher.setPhone(resultSet.getString(9));
                teacher.setSalary(resultSet.getInt(10));
                groups.setTeacher(teacher);
                Room room = new Room();
                room.setId(resultSet.getInt(11));
                room.setName(resultSet.getString(12));
                room.setCapacity(resultSet.getInt(13));
                groups.setRoom(room);
                groups.setStart_date(resultSet.getDate(14));
                groups.setEnd_date(resultSet.getDate(15));
                Status status = new Status();
                status.setId(16);
                groups.setStatus(status);
                Student student = new Student();
                student.setId(resultSet.getInt(17));
                student.setFull_name(resultSet.getString(18));
                student.setPhone(resultSet.getString(19));
                Group_student group_student = new Group_student(groups,student);
                group_students.add(group_student);
            }
            return group_students;
        } catch (SQLException e) {
         throw new RuntimeException(e);
        }
    }
}
