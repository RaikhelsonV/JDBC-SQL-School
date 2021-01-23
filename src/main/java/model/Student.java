package model;


import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private int group_id;
    private List<Course> courses;
    private String first_name;
    private String last_name;

    public Student() {
        courses = new ArrayList<Course>();
    }

    public Student(int group_id, List<Course> courses, String first_name, String last_name) {
        this.group_id = group_id;
        this.courses = courses;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student(String first_name, String last_name, int group_id) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.group_id = group_id;
    }

    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", courses=" + courses +

                '}';
    }
}
