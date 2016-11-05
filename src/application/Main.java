package application;

import application.controller.ApplicationController;
import application.model.GroupModel;
import application.model.StudentModel;
import application.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        new ApplicationController(new StudentModel(), new GroupModel(), new ConsoleView()).startApp();
    }
}
