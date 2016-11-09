package application.constants;


public class RegularExpressions {
    private RegularExpressions(){}
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String STUDENT_NAME = "[a-zA-Z ]+";
    public static final String GROUP_DEPARTMENT = "[a-zA-Z ]+";
    public static final String FILE_NAME = "[a-zA-Z0-9/:.]+";
    public static final String NAME_SEARCH_QUERY = "[a-zA-Z?* ]+";
    public static final String DATE_SEARCH_QUERY = "[0-9.?*]+";
    public static final String NUMBER_SEARCH_QUERY = "[0-9?*]+";
}
