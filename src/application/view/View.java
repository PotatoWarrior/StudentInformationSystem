package application.view;


import application.model.Group;
import application.model.Student;

import java.util.List;

public interface View {
    void addItemToMenu(int order, String item);
    int showMenuAndGetChoice();

    Student getStudent();
    Student getStudentName();
    void showStudents(List<Student> students);

    Group getGroup();
    void showGroups(List<Group> groups);

    String getSearchQuery();

    String getFileName();

    void showException(Exception e);
}
