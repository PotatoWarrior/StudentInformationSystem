package application.view;


import application.model.entity.Group;
import application.model.entity.Student;

import java.util.Date;
import java.util.List;

public interface View {
    void addItemToMenu(int order, String item);
    int showMenuAndGetChoice();

    Student getStudent();
    Student getStudentName();
    Date getEnrollmentDate();
    void showStudents(List<Student> students);

    Group getGroup();
    String getDepartment();
    void showGroups(List<Group> groups);

    String getNameSearchQuery();
    String getNumberSearchQuery();
    String getDateSearchQuery();

    String getFileName();

    void showException(Exception e);
}
