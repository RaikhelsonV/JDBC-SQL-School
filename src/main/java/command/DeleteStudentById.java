package command;

import db.dao.StudentDao;
import exception.NoSuchStudentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteStudentById implements Command {
    private StudentDao studentDao;

    public DeleteStudentById(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void execute() throws IOException, NoSuchStudentException {
        System.out.println("Enter student_id: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Integer student_id = null;

        while (!(student_id = Integer.parseInt(reader.readLine())).equals("exit")) {
            studentDao.removeStudent(student_id);
            System.out.println("student was deleted from DB successfully");
        }

    }
}
