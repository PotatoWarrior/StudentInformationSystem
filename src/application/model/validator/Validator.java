package application.model.validator;

public class Validator {
    public static boolean validateDate(String date){
        return new DateValidator().validate(date);
    }
    public static boolean validateStudentName(String name){
        return name.matches("[a-zA-Z ]+");
    }
    public static boolean validateGroupNumber(String number){
        try{
            return validateGroupNumber(Integer.parseInt(number));
        } catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean validateGroupNumber(int number){
        return number >= 0;
    }
    public static boolean validateGroupDepartment(String department){
        return department.matches("[a-zA-Z ]+");
    }
    public static boolean validateFileName(String file){
        return file.matches("[a-zA-Z0-9/:.]+");
    }
    public static boolean validateSearchQuery(String query){
        return query.matches("[a-zA-Z?* ]+");
    }
}
