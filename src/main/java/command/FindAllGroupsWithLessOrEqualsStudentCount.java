package command;

import db.dao.GroupDao;
import model.Group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindAllGroupsWithLessOrEqualsStudentCount implements Command {
    private GroupDao groupDao;

    public FindAllGroupsWithLessOrEqualsStudentCount(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Enter amount of students: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer amount = null;

        while (!(amount = Integer.parseInt(reader.readLine())).equals("exit")) {
            List<Group> allGroupsWithLessOrEqualsStudentCount = groupDao.getAllGroupsWithLessOrEqualsStudentCount(amount);
            for (Group group : allGroupsWithLessOrEqualsStudentCount) {
                System.out.println(group);
            }
        }
    }
}
