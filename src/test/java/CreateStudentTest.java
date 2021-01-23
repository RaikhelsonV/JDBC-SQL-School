import db.ConnectionPool;
import db.Schema;
import model.Course;
import model.Student;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateStudentTest {

    @Test
    public void shouldCreateStudent() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_STUDENT);
        String first_name = "val";
        String last_name = "r";
        Student student = new Student(first_name,last_name);

        stmt.setString(1, student.getFirst_name());
        stmt.setString(2, student.getLast_name());
        stmt.executeUpdate();

        assertEquals(first_name, student.getFirst_name());
        assertEquals(last_name, student.getLast_name());
    }

    @Test
    public void findStudent() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_BY_ID);
        int id = 12;
        String first_name = "val";
        String last_name = "r";

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertEquals(first_name, rs.getString("first_name"));
        assertEquals(last_name, rs.getString("last_name"));


    }


}
