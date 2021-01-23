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

public class AddStudentToTheCourseTest {
    @Test
    public void shouldInsertStudentToTheCourse() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_STUDENT_COURSE);
        int student_id = 3;
        String course_name = "Bel";
        Student student = new Student(student_id);
        Course course = new Course(course_name);

        stmt.setInt(1, student_id);
        stmt.setString(2, course_name);
        stmt.executeUpdate();

        assertEquals(student_id, student.getId());
        assertEquals(course_name, course.getName());
    }
    @Test
    public void findCourse() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_STUDENT_COURSES);
        int student_id = 3;
        String course_name = "Bel";

        stmt.setInt(1, student_id);
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertEquals(student_id, rs.getInt("student_id"));
        assertEquals(course_name, rs.getString("course_name"));


    }
}
