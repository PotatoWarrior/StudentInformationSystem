package application.controller;

import application.constants.MenuItems;
import application.exceptions.DaoException;
import application.exceptions.IncorrectFileException;
import application.exceptions.IncorrectSearchQuery;
import application.model.Group;
import application.model.Model;
import application.model.Student;
import application.view.View;

import java.util.List;

public class ApplicationController implements Controller {
    private Model<Student> studentModel;
    private Model<Group> groupModel;
    private View view;
    public ApplicationController(Model<Student> studentModel, Model<Group> groupModel, View view){
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
                    searchStudent();
                    break;
                case 5:
                    addGroup();
                    break;
                case 6:
                    deleteGroup();
                    break;
                case 7:
                    updateGroup();
                    break;
                case 8:
                    showGroups();
                    break;
                case 9:
                    searchGroup();
                    break;
                case 10:
                    save();
                    break;
                case 11:
                    addFromFile();
                    break;
                case 12:
                    System.exit(0);
            }
        }

    }

    private void initMenu(){
        view.addItemToMenu(0, MenuItems.ADD_STUDENT);
        view.addItemToMenu(1, MenuItems.DELETE_STUDENT);
        view.addItemToMenu(2, MenuItems.UPDATE_STUDENT);
        view.addItemToMenu(3, MenuItems.SHOW_STUDENTS);
        view.addItemToMenu(4, MenuItems.SEARCH_STUDENT);
        view.addItemToMenu(5, MenuItems.ADD_GROUP);
        view.addItemToMenu(6, MenuItems.DELETE_GROUP);
        view.addItemToMenu(7, MenuItems.UPDATE_GROUP);
        view.addItemToMenu(8, MenuItems.SHOW_GROUPS);
        view.addItemToMenu(9, MenuItems.SEARCH_GROUPS);
        view.addItemToMenu(10, MenuItems.SAVE_FILE);
        view.addItemToMenu(11, MenuItems.ADD_FILE);
        view.addItemToMenu(12, MenuItems.EXIT);
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
    private void searchStudent(){
        while(true){
            String query = this.view.getSearchQuery();
            try {
                List<Student> result = this.studentModel.search(query);
                this.view.showStudents(result);
                break;
            } catch (IncorrectSearchQuery e) {
                this.view.showException(e);
            }
        }
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
    private void searchGroup() {
        while(true){
            String query = this.view.getSearchQuery();
            try {
                List<Group> result = this.groupModel.search(query);
                this.view.showGroups(result);
                break;
            } catch (IncorrectSearchQuery e) {
                this.view.showException(e);
            }
        }
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
