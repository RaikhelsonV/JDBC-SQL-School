package command;

import db.dao.GroupDao;
import model.Group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateGroup implements Command {
    private GroupDao groupDao;

    public CreateGroup(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Enter the name of the group: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";

        while (!(name = reader.readLine()).equals("exit")) {
            Group group = new Group(name);
            groupDao.createGroup(group);
            System.out.println(group.getName() + " was inserted to DB successfully");

        }

    }
}
