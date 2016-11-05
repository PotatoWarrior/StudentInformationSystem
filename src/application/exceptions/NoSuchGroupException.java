package application.exceptions;


public class NoSuchGroupException extends DaoException {
    @Override
    public String toString() {
        return "Group not found";
    }
}
