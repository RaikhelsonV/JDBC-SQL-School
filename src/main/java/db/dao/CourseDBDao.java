package db.dao;


import db.ConnectionPool;
import db.Schema;
import model.Course;
import model.Student;
import exception.NoSuchStudentException;
import exception.SystemMalfunctionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDBDao implements CourseDao {

    @Override
    public void createCourse(Course course) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_COURSE);
            applyCourseValuesOnStatement(stmt, course);
            stmt.executeUpdate();

        } catch (SQLException e) {
            String message = String.format("Unable to create course! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    @Override
    public List<Student> getAllStudentsWithGivenName(String course_name) throws NoSuchStudentException {
        Connection connection = null;
        List<Student> students = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_COURSE_STUDENTS);

            stmt.setString(1, course_name);

            ResultSet resultSet = stmt.executeQuery();
            students = new ArrayList<Student>();

            while (resultSet.next()) {
                Student student = resultSetToStudent(resultSet);
                students.add(student);
            }

        } catch (SQLException e) {
            String message = String.format("Unable to get course (%s) with students ", course_name);
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
        return students;

    }

    @Override
    public void removeStudent(String course_name,int student_id) throws NoSuchStudentException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.DELETE_STUDENT_FROM_COURSE);
            stmt.setString(1, course_name);
            stmt.setInt(2, student_id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                String message = String.format("No such student with id: %d", student_id);
                throw new NoSuchStudentException(message);
            }else System.out.println("student was successfully removed from the course!");
        } catch (SQLException e) {
            String message = String.format("Unable to delete student! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }


    }

    private static Student resultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt(1));
        student.setFirst_name(resultSet.getString(2));
        student.setLast_name(resultSet.getString(3));

        return student;
    }

    private static void applyCourseValuesOnStatement(PreparedStatement stmt, Course course) throws SQLException {
        stmt.setString(1, course.getName());
        stmt.setString(2, course.getDescription());
    }

}
