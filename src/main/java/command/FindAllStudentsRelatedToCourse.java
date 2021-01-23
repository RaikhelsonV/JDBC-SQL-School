package command;

import db.dao.CourseDao;
import model.Student;
import exception.NoSuchStudentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindAllStudentsRelatedToCourse implements Command {
    private CourseDao courseDao;

    public FindAllStudentsRelatedToCourse(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void execute() throws IOException, NoSuchStudentException {
        System.out.println("Enter course_name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String course_name = "";

        while (!(course_name = reader.readLine()).equals("exit")) {
            List<Student> students = courseDao.getAllStudentsWithGivenName(course_name);
            System.out.println("course #" + course_name + " students: ");
            for (Student student : students) {
                System.out.print(student.getFirst_name() + " " + student.getLast_name() + "\n");
            }
        }
    }
}
