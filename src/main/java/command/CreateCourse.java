package command;

import db.dao.CourseDao;
import model.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateCourse implements Command {
    private CourseDao courseDao;

    public CreateCourse(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Enter the name of the course and its description: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        String description = "";

        while (!(name = reader.readLine()).equals("exit")) {
            description = reader.readLine();
            Course course = new Course(name, description);
            courseDao.createCourse(course);
            System.out.println(course.getName() + " was inserted to DB successfully");

        }
    }
}
