import db.ConnectionPool;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class ConnectionPoolTest {
    private ConnectionPool connectionPool;

    @BeforeEach
    void init() {
        connectionPool = new ConnectionPool();
    }

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try (Connection connection = connectionPool.getInstance().getConnection();) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

}
