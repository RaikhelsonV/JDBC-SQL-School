import db.ConnectionPool;

import java.sql.Connection;

public class Main_CheckConnection {
    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        System.out.println("I was able to get a connection!");
    }
}
