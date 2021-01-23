package command;

import db.dao.CourseDao;
import exception.NoSuchStudentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTheStudentFromCourse implements Command {
    private CourseDao courseDao;

    public RemoveTheStudentFromCourse(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void execute() throws IOException, NoSuchStudentException {
        System.out.println("Enter course_name and student_id: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String course_name = "";
        Integer student_id = null;

        while (!(course_name = reader.readLine()).equals("exit")) {
            student_id = Integer.parseInt(reader.readLine());
            courseDao.removeStudent(course_name, student_id);
        }

    }
}
