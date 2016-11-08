package application.exceptions;


import application.constants.ExceptionConstants;

public class NoSuchGroupException extends DaoException {
    @Override
    public String toString() {
        return ExceptionConstants.NO_SUCH_GROUP;
    }
}
