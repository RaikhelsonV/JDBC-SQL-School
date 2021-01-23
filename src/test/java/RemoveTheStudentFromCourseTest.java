import db.ConnectionPool;
import db.Schema;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class RemoveTheStudentFromCourseTest {
    @Test
    public void shouldRemoveTheStudentFromCourse() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.DELETE_STUDENT_FROM_COURSE);
        String course_name = "Bel";
        int student_id = 3;
        stmt.setInt(2, student_id);
        stmt.setString(1, course_name);
        stmt.executeUpdate();

    }

    @Test
    public void findStudent() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_COURSES);
        int student_id = 3;
        stmt.setInt(1, student_id);
        ResultSet rs = stmt.executeQuery();
        assertFalse(rs.next());


    }

}
