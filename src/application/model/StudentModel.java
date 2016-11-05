package application.model;

import application.exceptions.IncorrectFileException;
import application.exceptions.NoSuchStudentException;
import application.exceptions.StudentAlreadyExistsException;
import application.model.dao.StudentGroupDao;
import application.model.dao.XMLStudentGroupDao;

import java.util.ArrayList;
import java.util.List;

public class StudentModel implements Model<Student>{
    private StudentGroupDao dao = XMLStudentGroupDao.getInstance();
    @Override
    public void add(Student item) throws StudentAlreadyExistsException {
        dao.addStudent(item);
    }

    @Override
    public void update(Student item1, Student item2) throws NoSuchStudentException, StudentAlreadyExistsException {
        dao.updateStudent(item1, item2);
    }

    @Override
    public void delete(Student item) throws NoSuchStudentException {
        dao.deleteStudent(item);
    }

    @Override
    public List<Student> getAll() {
        return dao.getStudents();
    }

    @Override
    public List<Student> search(String query) {
        query = query.replace("*", ".*").replace("?", ".?");
        List<Student> result = new ArrayList<>();
        for(Student s : dao.getStudents())
            if(s.getName().matches(query))
                result.add(s);
        return result;
    }

    @Override
    public void save(String file) throws IncorrectFileException {
        dao.save(file);
    }

    @Override
    public void addAll(String file) throws IncorrectFileException {
        dao.addAll(file);
    }
}
