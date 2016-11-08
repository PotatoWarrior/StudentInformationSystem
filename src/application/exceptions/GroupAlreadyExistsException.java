package application.exceptions;


import application.constants.ExceptionConstants;

public class GroupAlreadyExistsException extends DaoException {
    @Override
    public String toString() {
        return ExceptionConstants.GROUP_ALREADY_EXISTS;
    }
}
