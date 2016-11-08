package application.exceptions;


import application.constants.ExceptionConstants;

public class IncorrectFileException extends DaoException {
    @Override
    public String toString() {
        return ExceptionConstants.INCORRECT_FILE;
    }
}
