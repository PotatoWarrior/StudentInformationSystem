package application.model;


import application.exceptions.DaoException;
import application.exceptions.IncorrectFileException;

import java.util.List;

public interface Model<T> {
    void add(T item) throws DaoException;
    void update(T item1, T item2) throws DaoException;
    void delete(T item) throws DaoException;
    List<T> getAll();
    void save(String file) throws IncorrectFileException;
    void addAll(String file) throws IncorrectFileException;
}
