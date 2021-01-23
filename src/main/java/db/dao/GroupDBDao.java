package db.dao;

import db.ConnectionPool;
import db.Schema;
import model.Group;
import exception.SystemMalfunctionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupDBDao implements GroupDao {

    @Override
    public void createGroup(Group group) {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.INSERT_GROUP);
            applyGroupValuesOnStatement(stmt, group);
            stmt.executeUpdate();

        } catch (SQLException e) {
            String message = String.format("Unable to create group! (%s) ", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
    }

    @Override
    public List<Group> getAllGroupsWithLessOrEqualsStudentCount(int students_amount) {
        Connection connection = null;
        List<Group> groups = new ArrayList<Group>();
        Group group;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(Schema.SELECT_GROUPS_BY_STUDENT_AMOUNT);
            stmt.setInt(1, students_amount);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                group = new Group(resultSet.getString(2));
                group.setId(resultSet.getInt(1));
                groups.add(group);
            }
        } catch (SQLException e) {
            String message = String.format("Unable to get all students: %s", e.getMessage());
            throw new SystemMalfunctionException(message);
        } finally {
            ConnectionPool.getInstance().putConnection(connection);
        }
        return groups;
    }

    private static void applyGroupValuesOnStatement(PreparedStatement stmt, Group group) throws SQLException {
        stmt.setString(1, group.getName());
    }

}

