package application.model.validator;

import application.constants.RegularExpressions;

import java.text.SimpleDateFormat;

public class Validator {
    public static SimpleDateFormat getDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(RegularExpressions.DATE_FORMAT);
        dateFormat.setLenient(false);
        return dateFormat;
    }
    public static boolean validateStudentName(String name){
        return name.matches(RegularExpressions.STUDENT_NAME);
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
        return department.matches(RegularExpressions.GROUP_DEPARTMENT);
    }
    public static boolean validateFileName(String file){
        return file.matches(RegularExpressions.FILE_NAME);
    }
    public static boolean validateNameSearchQuery(String query){
        return query.matches(RegularExpressions.NAME_SEARCH_QUERY);
    }
    public static boolean validateNumberSearchQuery(String query){
        return query.matches(RegularExpressions.NUMBER_SEARCH_QUERY);
    }
    public static boolean validateDateSearchQuery(String query){
        return query.matches(RegularExpressions.DATE_SEARCH_QUERY);
    }
}
