package application.controller;

import application.constants.MenuItems;
import application.exceptions.DaoException;
import application.exceptions.IncorrectFileException;
import application.model.GroupModel;
import application.model.StudentModel;
import application.model.entity.Group;
import application.model.entity.Student;
import application.view.View;

import java.util.List;

public class ApplicationController implements Controller {
    private StudentModel studentModel;
    private GroupModel groupModel;
    private View view;
    public ApplicationController(StudentModel studentModel, GroupModel groupModel, View view){
        this.studentModel = studentModel;
        this.groupModel = groupModel;
        this.view = view;
    }
    @Override
    public void startApp() {
        initMenu();
        while(true){
            int choice = view.showMenuAndGetChoice();
            switch (choice) {
                case 0:
                    addStudent();
                    break;
                case 1:
                    deleteStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    showStudents();
                    break;
                case 4:
                    searchStudentByName();
                    break;
                case 5:
                    searchStudentByGroup();
                    break;
                case 6:
                    searchStudentByDepartment();
                    break;
                case 7:
                    searchStudentByDate();
                    break;
                case 8:
                    addGroup();
                    break;
                case 9:
                    deleteGroup();
                    break;
                case 10:
                    updateGroup();
                    break;
                case 11:
                    showGroups();
                    break;
                case 12:
                    searchGroupByDepartment();
                    break;
                case 13:
                    save();
                    break;
                case 14:
                    addFromFile();
                    break;
                case 15:
                    System.exit(0);
            }
        }

    }

    private void initMenu(){
        view.addItemToMenu(0, MenuItems.ADD_STUDENT);
        view.addItemToMenu(1, MenuItems.DELETE_STUDENT);
        view.addItemToMenu(2, MenuItems.UPDATE_STUDENT);
        view.addItemToMenu(3, MenuItems.SHOW_STUDENTS);
        view.addItemToMenu(4, MenuItems.SEARCH_STUDENT_BY_NAME);
        view.addItemToMenu(5, MenuItems.SEARCH_STUDENT_BY_GROUP);
        view.addItemToMenu(6, MenuItems.SEARCH_STUDENT_BY_DEPARTMENT);
        view.addItemToMenu(7, MenuItems.SEARCH_STUDENT_BY_DATE);
        view.addItemToMenu(8, MenuItems.ADD_GROUP);
        view.addItemToMenu(9, MenuItems.DELETE_GROUP);
        view.addItemToMenu(10, MenuItems.UPDATE_GROUP);
        view.addItemToMenu(11, MenuItems.SHOW_GROUPS);
        view.addItemToMenu(12, MenuItems.SEARCH_GROUPS_BY_DEPARTMENT);
        view.addItemToMenu(13, MenuItems.SAVE_FILE);
        view.addItemToMenu(14, MenuItems.ADD_FILE);
        view.addItemToMenu(15, MenuItems.EXIT);
    }
    private void addStudent(){
        while(true){
            Student s = this.view.getStudent();
            try {
                this.studentModel.add(s);
                break;
            } catch (DaoException e) {
                view.showException(e);
            }
        }
    }
    private void deleteStudent(){
        while(true){
            Student s = this.view.getStudentName();
            try {
                this.studentModel.delete(s);
                break;
            } catch (DaoException e) {
                this.view.showException(e);
            }
        }
    }
    private void updateStudent(){
        while(true){
            Student oldStudent = this.view.getStudentName();
            Student newStudent = this.view.getStudent();
            try {
                this.studentModel.update(oldStudent, newStudent);
                break;
            } catch (DaoException e) {
                this.view.showException(e);
            }
        }
    }
    private void showStudents(){
        view.showStudents(this.studentModel.getAll());
    }
    private void searchStudentByName(){
        String query = this.view.getNameSearchQuery();
        List<Student> result = this.studentModel.searchByName(query);
        this.view.showStudents(result);
    }
    private void searchStudentByGroup(){
        String numberQuery = this.view.getNumberSearchQuery();
        String departmentQuery = this.view.getNameSearchQuery();
        List<Student> result = this.studentModel.searchByGroup(numberQuery, departmentQuery);
        this.view.showStudents(result);
    }
    private void searchStudentByDepartment(){
        String query = this.view.getNameSearchQuery();
        List<Student> result = this.studentModel.searchByDepartment(query);
        this.view.showStudents(result);
    }
    private void searchStudentByDate(){
        String dateQuery = this.view.getDateSearchQuery();
        List<Student> result = this.studentModel.searchByDate(dateQuery);
        this.view.showStudents(result);
    }
    private void addGroup(){
        while(true){
            Group g = this.view.getGroup();
            try {
                this.groupModel.add(g);
                break;
            } catch (DaoException e) {
                view.showException(e);
            }
        }
    }
    private void deleteGroup(){
        while(true){
            Group g = this.view.getGroup();
            try {
                this.groupModel.delete(g);
                break;
            } catch (DaoException e) {
                this.view.showException(e);
            }
        }
    }
    private void updateGroup(){
        while(true){
            Group oldGroup = this.view.getGroup();
            Group newGroup = this.view.getGroup();
            try {
                this.groupModel.update(oldGroup, newGroup);
                break;
            } catch (DaoException e) {
                this.view.showException(e);
            }
        }
    }
    private void showGroups(){
        view.showGroups(this.groupModel.getAll());
    }
    private void searchGroupByDepartment() {
        String query = this.view.getNameSearchQuery();
        List<Group> result = this.groupModel.searchByDepartment(query);
        this.view.showGroups(result);
    }
    private void addFromFile(){
        while(true){
            String file = this.view.getFileName();
            try{
                this.studentModel.addAll(file);
                break;
            } catch (IncorrectFileException e){
                this.view.showException(e);
            }
        }
    }
    private void save(){
        while(true){
            String file = this.view.getFileName();
            try{
                this.studentModel.save(file);
                break;
            } catch (IncorrectFileException e){
                this.view.showException(e);
            }
        }
    }
}
