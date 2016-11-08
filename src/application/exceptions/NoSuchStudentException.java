package application.exceptions;


import application.constants.ExceptionConstants;

public class NoSuchStudentException extends DaoException {
    @Override
    public String toString() {
        return ExceptionConstants.NO_SUCH_STUDENT;
    }
}
