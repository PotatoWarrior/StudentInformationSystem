package application.model;

import application.exceptions.IncorrectFileException;
import application.exceptions.NoSuchStudentException;
import application.exceptions.StudentAlreadyExistsException;
import application.model.dao.StudentGroupDao;
import application.model.dao.XMLStudentGroupDao;
import application.model.entity.Group;
import application.model.entity.Student;
import application.model.validator.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentModelImpl implements StudentModel{
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
    public List<Student> searchByName(String query) {
        query = query.replace("*", ".*").replace("?", ".?");
        List<Student> result = new ArrayList<>();
        for(Student s : dao.getStudents())
            if(s.getName().matches(query))
                result.add(s);
        return result;
    }

    @Override
    public List<Student> searchByGroup(String numberQuery, String departmentQuery) {
        numberQuery = numberQuery.replace("*", ".*").replace("?", ".?");
        departmentQuery = departmentQuery.replace("*", ".*").replace("?", ".?");
        List<Student> result = new ArrayList<>();
        for(Student s : dao.getStudents()){
            Group g = s.getGroup();
            if(String.valueOf(g.getNumber()).matches(numberQuery) && g.getDepartment().matches(departmentQuery))
                result.add(s);
        }
        return result;
    }

    @Override
    public List<Student> searchByDepartment(String query) {
        query = query.replace("*", ".*").replace("?", ".?");
        List<Student> result = new ArrayList<>();
        for(Student s : dao.getStudents())
            if(s.getGroup().getDepartment().matches(query))
                result.add(s);
        return result;
    }

    @Override
    public List<Student> searchByDate(String dateQuery) {
        dateQuery = dateQuery.replace(".", "\\.").replace("*", ".*").replace("?", ".?");
        List<Student> result = new ArrayList<>();
        SimpleDateFormat dateFormat = Validator.getDateFormat();
        for(Student s : dao.getStudents()){
            String date = dateFormat.format(s.getEnrollmentDate());
            if(date.matches(dateQuery))
                result.add(s);
        }
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
