import command.*;
import db.dao.*;
import exception.NoSuchStudentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchStudentException {
        CourseDao courseDao = new CourseDBDao();
        StudentDao studentDao = new StudentDBDao();
        GroupDao groupDao = new GroupDBDao();
        Menu menu = new Menu();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (!input.equalsIgnoreCase("stop")) {
            showMenu();
            input = reader.readLine();
            menu.setCommand("1", new CreateCourse(courseDao));
            menu.setCommand("2", new CreateStudent(studentDao));
            menu.setCommand("3", new CreateGroup(groupDao));
            menu.setCommand("4", new AddStudentToTheCourse(studentDao));
            menu.setCommand("5", new FindAllStudentsRelatedToCourse(courseDao));
            menu.setCommand("6", new DeleteStudentById(studentDao));
            menu.setCommand("7", new RemoveTheStudentFromCourse(courseDao));
            menu.setCommand("8", new FindAllGroupsWithLessOrEqualsStudentCount(groupDao));
            menu.runCommand(input);
        }
    }

    private static void showMenu() {
        System.out.println(
                        "1 - Create course.\n" +
                        "2 - Add new student.\n" +
                        "3 - Create group.\n" +
                        "4 - Add a student to the course (from a list).\n" +
                        "5 - Find all students related to course with given name.\n" +
                        "6 - Delete student by STUDENT_ID.\n" +
                        "7 - Remove the student from one of his or her courses.\n" +
                        "8 - Find all groups with less or equals student count.\n" +
                        "Enter 'exit' to return to the menu.\n" +
                        "Enter 'stop' to exit the program.\n" +
                        "\n" +
                        "Please, enter the corresponding number: ");
    }
}





