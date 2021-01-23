package command;

import db.dao.StudentDao;
import model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateStudent implements Command {
    private StudentDao studentDao;

    public CreateStudent(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Enter first_name, last_name and group_id of the student: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String first_name = "";
        String last_name = "";
//        Integer group_id = null;

        while (!(first_name = reader.readLine()).equals("exit")) {
            last_name = reader.readLine();
//            group_id= Integer.parseInt(reader.readLine());

            Student student = new Student(first_name, last_name);
            studentDao.addNewStudent(student);
            System.out.println(student.getLast_name() + " was inserted to DB successfully");


        }
    }

}

