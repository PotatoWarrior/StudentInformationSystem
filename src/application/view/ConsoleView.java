package application.view;


import application.model.Group;
import application.model.Student;
import application.model.validator.Validator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View{
    private Scanner in = new Scanner(System.in);
    private PrintStream out = System.out;

    private class MenuItem{
        private String item;
        private int order;

        private MenuItem(String item, int order) {
            this.item = item;
            this.order = order;
        }

        @Override
        public String toString() {
            return this.order + ") " + this.item;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(!(obj instanceof MenuItem)) return false;
            return this.order == ((MenuItem)obj).order;
        }
    }
    private List<MenuItem> items;

    public ConsoleView(){
        items = new ArrayList<>();
    }

    @Override
    public void addItemToMenu(int order, String item) {
        MenuItem menuItem = new MenuItem(item, order);
        if(this.items.contains(menuItem)) throw new IllegalArgumentException("Item with this order already exists");
        this.items.add(menuItem);
    }

    @Override
    public int showMenuAndGetChoice() {
        int choice = getMenuChoiceInput();
        return choice;
    }
    private int getMenuChoiceInput(){
        while(true){
            showMenu();
            String number = in.next().trim();
            try{
                int choice = Integer.parseInt(number);
                if(menuRangeCheck(choice)) return choice;
            } catch (NumberFormatException e){

            }
        }
    }
    private boolean menuRangeCheck(int i){
        for(MenuItem item : this.items)
            if(item.order == i) return true;
        return false;
    }
    private void showMenu(){
        clearScreen();
        for(MenuItem item : items){
            out.println(item);
        }
    }
    private void clearScreen(){
        for(int count = 0;count < 20;count++){
            out.println();
        }
    }
    @Override
    public Student getStudent() {
        Student s = new Student();
        Group g = new Group();

        String name = getStudentNameInput();
        s.setName(name);

        String data = getDateInput();
        s.setEnrollMentDate(data);

        int number = getGroupNumberInput();
        g.setNumber(number);

        String department = getDepartmentInput();
        g.setDepartment(department);

        s.setGroup(g);
        return s;
    }

    @Override
    public Student getStudentName() {
        Student s = new Student();
        String name = getStudentNameInput();
        s.setName(name);
        return s;
    }

    @Override
    public void showStudents(List<Student> students) {
        clearScreen();
        for(Student s : students)
            out.println(s);
        in.next();
    }

    @Override
    public Group getGroup() {
        Group g = new Group();

        int number = getGroupNumberInput();
        g.setNumber(number);

        String department = getDepartmentInput();
        g.setDepartment(department);

        return g;
    }

    @Override
    public void showGroups(List<Group> groups) {
        clearScreen();
        for(Group g : groups)
            out.println(g);
        in.next();
    }

    @Override
    public String getSearchQuery() {
        String query = getSearchQueryInput();
        return query;
    }

    @Override
    public String getFileName() {
        String file = getFileNameInput();
        return file;
    }

    @Override
    public void showException(Exception e) {
        clearScreen();
        out.println(e);
        in.next();
    }
    private String getDateInput(){
        clearScreen();
        while (true) {
            out.print("Enter enrollment date(dd.mm.yyyy): ");
            String date = in.next().trim();
            if (Validator.validateDate(date)) return date;
            else {
                clearScreen();
                out.println("Wrong date format, try again...\n");
            }
        }
    }
    private String getStudentNameInput(){
        clearScreen();
        while (true) {
            out.print("Enter student name(A-Z): ");
            String name = in.next().trim();
            if (Validator.validateStudentName(name)) return name;
            else {
                clearScreen();
                out.println("Incorrect name, try again...\n");
            }
        }
    }
    private String getDepartmentInput(){
        clearScreen();
        while(true){
            out.print("Enter department name(A-Z): ");
            String department = in.next().trim();
            if(Validator.validateGroupDepartment(department)) return department;
            else {
                clearScreen();
                out.println("Incorrect department name, try again...\n");
            }
        }
    }
    private int getGroupNumberInput(){
        clearScreen();
        while(true){
            out.print("Enter group number: ");
            String number = in.next().trim();
            if(Validator.validateGroupNumber(number)) return Integer.parseInt(number);
            else {
                clearScreen();
                out.println("Incorrect group number, try again...\n");
            }
        }
    }
    private String getFileNameInput(){
        clearScreen();
        while(true){
            out.print("Enter file name: ");
            String file = in.next().trim();
            if(Validator.validateFileName(file)) return file;
            else {
                clearScreen();
                out.println("Incorrect file name, try again...\n");
            }
        }
    }
    private String getSearchQueryInput(){
        clearScreen();
        while(true){
            out.print("* - any number of symbols \n ? - 0 or 1 symbol \n Enter search query: ");
            String query = in.next().trim();
            if(Validator.validateSearchQuery(query)) return query;
            else {
                clearScreen();
                out.println("Incorrect search query, try again...\n");
            }
        }
    }
}
