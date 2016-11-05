package application.exceptions;

public class IncorrectSearchQuery extends Exception {
    @Override
    public String toString() {
        return "Incorrect search query";
    }
}
