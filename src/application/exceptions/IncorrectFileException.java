package application.exceptions;


public class IncorrectFileException extends DaoException {
    @Override
    public String toString() {
        return "Incorrect file";
    }
}
