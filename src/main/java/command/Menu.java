package command;

import exception.NoSuchStudentException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Command> commands = new HashMap<>();

    public void setCommand(String type, Command command) {
        commands.put(type, command);
    }

    public void runCommand(String type) throws IOException, NoSuchStudentException {
        commands.get(type).execute();
    }

}
