package db.dao;

import db.ConnectionPool;
import db.Schema;
import model.Course;
import model.Student;
import exception.NoSuchStudentException;
import exception.StudentAlreadyEnrolledException;
import exception.SystemMalfunctionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDBDao implements StudentDao {
    @Override
    public void addNewStudent(Student student) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_STUDENT);
            applyStudentValuesOnStatement(stmt, student);
            stmt.executeUpdate();

        } catch (SQLException e) {
            String message = String.format("Unable to create student! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    @Override
    public void removeStudent(int id) throws NoSuchStudentException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.DELETE_STUDENT);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                String message = String.format("No such student with id: %d", id);
                throw new NoSuchStudentException(message);
            }else System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            String message = String.format("Unable to delete student! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    @Override
    public void updateStudent(Student student) throws NoSuchStudentException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.UPDATE_STUDENT);
            stmt.setString(1, student.getFirst_name());
            stmt.setString(2, student.getLast_name());
//            stmt.setObject(3,student.getGroup());
            stmt.setInt(3, student.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                String message = String.format("No such student with id: %d", student.getId());
                throw new NoSuchStudentException(message);
            }

        } catch (SQLException e) {
            String message = String.format("Unable to update student! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    @Override
    public List<Course> getCourses(int id) {
        Connection connection = null;
        List<Course> courses = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_COURSES);

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            courses = new ArrayList<Course>();

            while (resultSet.next()) {
                Course course = resultSetToCourse(resultSet);
                courses.add(course);
            }

        } catch (SQLException e) {
            String message = String.format("Unable to get student (%d) with courses ", id);
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
        return courses;
    }

    @Override
    public Student getStudent(int id) throws NoSuchStudentException {
        Connection connection = null;
        Student student = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_BY_ID);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                student = resultSetToStudent(resultSet);
//                student.setCourses(getCourses(id));
            } else {
                String message = String.format("No such student with id (%d)", id);
                throw new NoSuchStudentException(message);
            }

        } catch (SQLException e) {
            String message = String.format("Unable to get student (%s)", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        Connection connection = null;
        List<Student> students = new ArrayList<Student>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(Schema.SELECT_ALL_STUDENTS);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                students.add(getStudent(id));
            }
        } catch (SQLException e) {
            String message = String.format("Unable to get all students: %s", e.getMessage());
            throw new SystemMalfunctionException(message);
        } catch (NoSuchStudentException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
        return students;

    }

    @Override
    public void insertStudentCourse(int student_id, String course_name) throws StudentAlreadyEnrolledException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_STUDENT_COURSE);
            stmt.setInt(1, student_id);
            stmt.setString(2, course_name);


            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                String message = String.format("the student with id %d is already enrolled in the course with name %s", student_id, course_name);
                throw new StudentAlreadyEnrolledException(message);
            }

        } catch (SQLException e) {
            String message = String.format("Unable to insert student-course: %s", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    private static Student resultSetToStudent(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String first_name = resultSet.getString(2);
        String last_name = resultSet.getString(3);
        int group_id = resultSet.getInt(4);

        Student student = new Student(first_name, last_name, group_id);
        student.setId(id);

        return student;
    }

    private static Course resultSetToCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt(1));
        course.setName(resultSet.getString(3));
        course.setDescription(resultSet.getString(2));
        return course;
    }

    private static void applyStudentValuesOnStatement(PreparedStatement stmt, Student student) throws SQLException {
        stmt.setString(1, student.getFirst_name());
        stmt.setString(2, student.getLast_name());
    }
}
