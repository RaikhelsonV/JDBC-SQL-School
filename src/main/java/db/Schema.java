package db;

public class Schema {
    private static final String TABLE_GROUPS = "groups";
    private static final String TABLE_COURSES = "courses";
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_STUDENTS_COURSES = "students_courses";

    private static final String COL_GROUP_NAME = "name";
    private static final String COL_GROUP_ID = "id";
    private static final String COL_COURSE_ID = "id";
    private static final String COL_COURSE_NAME = "name";
    private static final String COL_COURSE_DESCRIPTION = "description";
    private static final String COL_STUDENT_ID = "id";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_SCOURSE_NAME = "course_name";
    private static final String COL_SSTUDENT_ID = "student_id";

    public static final String INSERT_GROUP = "insert into "
            + TABLE_GROUPS
            + "("
            + COL_GROUP_NAME
            + ")"
            + " values (?)";

    public static final String INSERT_COURSE = "insert into "
            + TABLE_COURSES
            + "("
            + COL_COURSE_NAME + ","
            + COL_COURSE_DESCRIPTION + ")"
            + " values (?,?)";

    public static final String INSERT_STUDENT = "insert into "
            + TABLE_STUDENTS
            + "("
            + COL_FIRST_NAME + ","
            + COL_LAST_NAME + ")"
            + " values (?,?)";


    public static final String DELETE_STUDENT = "delete from "
            + TABLE_STUDENTS
            + " where "
            + COL_STUDENT_ID
            + " = ?";

    public static final String UPDATE_STUDENT = "update "
            + TABLE_STUDENTS +
            " set "
            + COL_FIRST_NAME + " = ?, "
            + COL_LAST_NAME + " =?"
            + "where "
            + COL_STUDENT_ID + "=?";

    public static final String SELECT_STUDENT_COURSES = "select * from "
            + TABLE_COURSES + " t1 "
            + " inner join "
            + TABLE_STUDENTS_COURSES + " t2"
            + " on "
            + "t1." + COL_COURSE_NAME + " = " + "t2." + COL_SCOURSE_NAME
            + " where "
            + "t2." + COL_SSTUDENT_ID + " = ?";

    public static final String SELECT_STUDENT_BY_ID = "select * from "
            + TABLE_STUDENTS +
            " where "
            + COL_STUDENT_ID + " =?";
    public static final String SELECT_COURSE_BY_NAME = "select * from "
            + TABLE_COURSES +
            " where "
            + COL_COURSE_NAME + " =?";
    public static final String SELECT_GROUP_BY_NAME = "select * from "
            + TABLE_GROUPS +
            " where "
            + COL_GROUP_NAME + " =?";
        public static final String SELECT_ALL_STUDENTS = "select "
            + COL_STUDENT_ID
            + " from "
            + TABLE_STUDENTS;

    public static final String INSERT_STUDENT_COURSE = "insert into "
            + TABLE_STUDENTS_COURSES
            + "("
            + COL_SSTUDENT_ID + ","
            + COL_SCOURSE_NAME
            + ")"
            + "values (?,?)";


    public static final String SELECT_GROUPS_BY_STUDENT_AMOUNT = "select "
            + COL_GROUP_ID
            + " , count(*) from "
            + TABLE_STUDENTS
            + " group by "
            + COL_GROUP_ID
            + " having count(*) <= (?) order by "
            + COL_GROUP_ID;

    public static final String SELECT_COURSE_STUDENTS = "select * from "
            + TABLE_STUDENTS + " t1"
            + " inner join "
            + TABLE_STUDENTS_COURSES + " t2"
            + " on "
            + "t1." + COL_STUDENT_ID + " = " + "t2." + COL_SSTUDENT_ID
            + " where "
            + "t2." + COL_SCOURSE_NAME + " = ?";
    public static final String DELETE_STUDENT_FROM_COURSE = "delete from "
            + TABLE_STUDENTS_COURSES
            + " where "
            + COL_SCOURSE_NAME
            + " = ? and "
            + COL_SSTUDENT_ID
            + " = ?";


}
