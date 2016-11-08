package application.exceptions;

import application.constants.ExceptionConstants;

public class IncorrectSearchQuery extends Exception {
    @Override
    public String toString() {
        return ExceptionConstants.INCORRECT_SEARCH_QUERY;
    }
}
