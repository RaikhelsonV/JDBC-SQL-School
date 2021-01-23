package db.dao;

import model.Course;
import model.Student;
import exception.NoSuchStudentException;
import java.util.List;

public interface CourseDao {

    void createCourse(Course course);

    List<Student> getAllStudentsWithGivenName(String course_name) throws NoSuchStudentException;

    void removeStudent(String course_name, int student_id) throws NoSuchStudentException;


}
