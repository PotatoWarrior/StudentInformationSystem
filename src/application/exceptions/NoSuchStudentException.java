package application.exceptions;


public class NoSuchStudentException extends DaoException {
    @Override
    public String toString() {
        return "Student not found";
    }
}
