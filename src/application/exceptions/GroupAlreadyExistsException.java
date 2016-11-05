package application.exceptions;


public class GroupAlreadyExistsException extends DaoException {
    @Override
    public String toString() {
        return "Group already exists";
    }
}
