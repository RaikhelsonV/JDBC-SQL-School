import db.ConnectionPool;
import db.Schema;
import model.Course;
import model.Group;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateGroupTest {

    @Test
    public void shouldCreateGroup() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_GROUP);
        String name = "CC-33";
        Group group = new Group(name);

        stmt.setString(1, group.getName());
        stmt.executeUpdate();

        assertEquals(name, group.getName());
    }

    @Test
    public void findGroup() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_GROUP_BY_NAME);
        String name = "CC-33";

        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertEquals(name, rs.getString("name"));
    }


}
