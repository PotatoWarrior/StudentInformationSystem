package application.exceptions;


public class StudentAlreadyExistsException extends DaoException {
    @Override
    public String toString() {
        return "Student already exists";
    }
}
