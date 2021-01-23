package command;

import exception.NoSuchStudentException;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, NoSuchStudentException;
}
