package application;

import application.controller.ApplicationController;
import application.model.GroupModelImpl;
import application.model.StudentModelImpl;
import application.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        new ApplicationController(new StudentModelImpl(), new GroupModelImpl(), new ConsoleView()).startApp();
    }
}
