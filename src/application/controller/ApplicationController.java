package application.controller;

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
            }
        }

    }

    private void initMenu(){
        view.addItemToMenu(0, "Add student");
        view.addItemToMenu(1, "Delete student");
        view.addItemToMenu(2, "Update student");
        view.addItemToMenu(3, "Show students");
        view.addItemToMenu(4, "Search student");
        view.addItemToMenu(5, "Add group");
        view.addItemToMenu(6, "Delete group");
        view.addItemToMenu(7, "Update group");
        view.addItemToMenu(8, "Show groups");
        view.addItemToMenu(9, "Search groups");
        view.addItemToMenu(10, "Save to file");
        view.addItemToMenu(11, "Add from file");
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
