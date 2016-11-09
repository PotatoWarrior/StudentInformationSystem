package application.model.dao;


import application.exceptions.*;
import application.model.entity.Group;
import application.model.entity.Student;

import java.util.List;

public interface StudentGroupDao {
    void addStudent(Student student) throws StudentAlreadyExistsException;
    void deleteStudent(Student student) throws NoSuchStudentException;
    void updateStudent(Student oldStudent, Student newStudent) throws NoSuchStudentException, StudentAlreadyExistsException;
    List<Student> getStudents();

    void addGroup(Group group) throws GroupAlreadyExistsException;
    void deleteGroup(Group group) throws NoSuchGroupException;
    void updateGroup(Group oldGroup, Group newGroup) throws NoSuchGroupException, GroupAlreadyExistsException;
    List<Group> getGroups();

    void addAll(String file) throws IncorrectFileException;
    void save(String file) throws IncorrectFileException;
}
