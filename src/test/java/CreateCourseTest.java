import db.ConnectionPool;
import db.Schema;
import model.Course;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourseTest {

    @Test
    public void shouldCreateCourse() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_COURSE);
        String name = "ad5d";
        String description = "b";
        Course course = new Course(name, description);

        stmt.setString(1, course.getName());
        stmt.setString(2, course.getDescription());
        stmt.executeUpdate();

        assertEquals(name, course.getName());
        assertEquals(description, course.getDescription());
    }

    @Test
    public void findCourse() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_COURSE_BY_NAME);
        String name = "ad5d";
        String description = "b";

        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertEquals(name, rs.getString("name"));
        assertEquals(description, rs.getString("description"));


    }


}
