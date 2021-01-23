import db.ConnectionPool;
import db.Schema;
import model.Student;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DeleteStudentByIdTest {
    @Test
    public void shouldDeleteStudentById() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.DELETE_STUDENT);
        int id = 12;
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Test
    public void findStudent() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_BY_ID);
        int id = 12;
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        assertFalse(rs.next());


    }

}
