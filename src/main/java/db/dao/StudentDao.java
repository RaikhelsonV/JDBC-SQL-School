package db.dao;

import model.Course;
import model.Student;
import exception.NoSuchStudentException;
import exception.StudentAlreadyEnrolledException;

import java.util.List;

public interface StudentDao {
    void addNewStudent(Student student);

    void removeStudent(int id) throws NoSuchStudentException;

    void updateStudent(Student student) throws NoSuchStudentException;

    List<Course> getCourses(int id);

    Student getStudent(int id) throws NoSuchStudentException;

    List<Student> getAllStudents();

    void insertStudentCourse(int student_id, String course_name) throws StudentAlreadyEnrolledException;



}
