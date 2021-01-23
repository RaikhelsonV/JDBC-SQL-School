package db;

import exception.SystemMalfunctionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.*;

public class ConnectionPool {
    private static final int MAX_CONNECTIONS = 1;
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connections;
    public static final String NAME_OF_PROPERTIES = "config.properties";
    public static final String PATH_TO_TABLE = "src/main/resources/createTables.sql";

    public ConnectionPool() throws SystemMalfunctionException {
        connections = new LinkedBlockingDeque<Connection>(MAX_CONNECTIONS);

        try {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                connections.offer(createConnection());
            }

        } catch (SQLException | IOException e) {
            String message = String.format("Unable to instantiate ConnectionPool (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        }
    }

    public synchronized Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            String message = String.format("Unable to get Connection! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        }
    }

    public synchronized void putConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            String message = String.format("Unable to put Connection! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        }

    }

    public synchronized void closeAllConnections() {
        Connection connection;
        try {
            while ((connection = connections.poll()) != null) {
                connection.close();
            }
        } catch (SQLException e) {
            String message = String.format("Unable to close all Connections! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        }
    }

    private Connection createConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(ReadingFileFromTheClasspath());

        String DB_URL = properties.getProperty("DB_URL");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        System.out.println("Creating connection to database...");
        Connection connection = DriverManager.getConnection(
                DB_URL,
                user,
                password);

        return connection;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private InputStream ReadingFileFromTheClasspath(){
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(NAME_OF_PROPERTIES);

    }

}
