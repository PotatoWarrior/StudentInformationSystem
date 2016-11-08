package application.exceptions;


import application.constants.ExceptionConstants;

public class StudentAlreadyExistsException extends DaoException {
    @Override
    public String toString() {
        return ExceptionConstants.STUDENT_ALREADY_EXISTS;
    }
}
