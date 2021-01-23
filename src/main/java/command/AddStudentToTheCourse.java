package command;

import db.dao.StudentDao;
import exception.StudentAlreadyEnrolledException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddStudentToTheCourse implements Command {
    private StudentDao studentDao;

    public AddStudentToTheCourse(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Enter student_id  and course_name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer student_id = null;
        String course_name = "";

        while (!(student_id = Integer.parseInt(reader.readLine())).equals("exit")) {
            course_name = reader.readLine();
            try {
                studentDao.insertStudentCourse(student_id, course_name);
                System.out.printf("\nThe student is enrolled in the course with name %s", course_name);
            } catch (StudentAlreadyEnrolledException e) {
                System.out.println(e.getMessage());
            }


        }

    }
}
