package application.view;


import application.constants.ExceptionConstants;
import application.constants.ViewConstants;
import application.model.entity.Group;
import application.model.entity.Student;
import application.model.validator.Validator;


import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

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
        if(this.items.contains(menuItem)) throw new IllegalArgumentException(ExceptionConstants.MENU_EXCEPTION);
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
            String number = in.nextLine().trim();
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

        Date date = getDateInput();
        s.setEnrollmentDate(date);

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
    public Date getEnrollmentDate() {
        Date date = getDateInput();
        return date;
    }

    @Override
    public void showStudents(List<Student> students) {
        clearScreen();
        for(Student s : students)
            out.println(s);
        out.println(ViewConstants.BACK_TO_MENU);
        in.nextLine();
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
    public String getDepartment() {
        String department = getDepartmentInput();
        return department;
    }

    @Override
    public void showGroups(List<Group> groups) {
        clearScreen();
        for(Group g : groups)
            out.println(g);
        out.println(ViewConstants.BACK_TO_MENU);
        in.nextLine();
    }

    @Override
    public String getNameSearchQuery() {
        String query = getSearchQueryInput(Validator::validateNameSearchQuery, ViewConstants.QUERY_FOR_NAME);
        return query;
    }

    @Override
    public String getNumberSearchQuery() {
        String query = getSearchQueryInput(Validator::validateNumberSearchQuery, ViewConstants.QUERY_FOR_NUMBER);
        return query;
    }

    @Override
    public String getDateSearchQuery() {
        String query = getSearchQueryInput(Validator::validateDateSearchQuery, ViewConstants.QUERY_FOR_DATE);
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
        out.println(ViewConstants.AFTER_EXCEPTION);
        in.nextLine();
    }
    private Date getDateInput(){
        SimpleDateFormat dateFormat = Validator.getDateFormat();
        String pattern = dateFormat.toPattern();

        clearScreen();
        while (true) {
            out.print(ViewConstants.ENTER_DATE + "(" + pattern + "): ");
            String date = in.nextLine().trim();
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
                clearScreen();
                out.println(ViewConstants.WRONG_DATE_FORMAT);
                out.println();
            }
        }
    }
    private String getStudentNameInput(){
        clearScreen();
        while (true) {
            out.print(ViewConstants.ENTER_NAME);
            String name = in.nextLine().trim();
            if (Validator.validateStudentName(name)) return name;
            else {
                clearScreen();
                out.println(ViewConstants.INCORRECT_NAME);
                out.println();
            }
        }
    }
    private String getDepartmentInput(){
        clearScreen();
        while(true){
            out.print(ViewConstants.ENTER_DEPARTMENT);
            String department = in.nextLine().trim();
            if(Validator.validateGroupDepartment(department)) return department;
            else {
                clearScreen();
                out.println(ViewConstants.INCORRECT_DEPARTMENT);
                out.println();
            }
        }
    }
    private int getGroupNumberInput(){
        clearScreen();
        while(true){
            out.print(ViewConstants.ENTER_NUMBER);
            String number = in.nextLine().trim();
            if(Validator.validateGroupNumber(number)) return Integer.parseInt(number);
            else {
                clearScreen();
                out.println(ViewConstants.INCORRECT_NUMBER);
                out.println();
            }
        }
    }
    private String getFileNameInput(){
        clearScreen();
        while(true){
            out.print(ViewConstants.FILE_NAME);
            String file = in.nextLine().trim();
            if(Validator.validateFileName(file)) return file;
            else {
                clearScreen();
                out.println(ViewConstants.INCORRECT_FILE);
                out.println();
            }
        }
    }
    private String getSearchQueryInput(Predicate<String> predicate, String msg){
        clearScreen();
        while(true){
            out.println(ViewConstants.SEARCH_QUERY_HELPER);
            out.print(msg);
            String query = in.nextLine().trim();
            if(predicate.test(query)) return query;
            else {
                clearScreen();
                out.println(ViewConstants.INCORRECT_SEARCH_QUERY);
                out.println();
            }
        }
    }
}
