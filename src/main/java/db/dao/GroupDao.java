package db.dao;

import model.Group;
import java.util.List;

public interface GroupDao {

    void createGroup(Group group);

    List<Group> getAllGroupsWithLessOrEqualsStudentCount(int students_amount);


}
