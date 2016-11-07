package application.model;


import application.exceptions.GroupAlreadyExistsException;
import application.exceptions.IncorrectFileException;
import application.exceptions.NoSuchGroupException;
import application.model.dao.StudentGroupDao;
import application.model.dao.XMLStudentGroupDao;

import java.util.ArrayList;
import java.util.List;

public class GroupModel implements Model<Group>{
    private StudentGroupDao dao = XMLStudentGroupDao.getInstance();
    @Override
    public void add(Group item) throws GroupAlreadyExistsException {
        dao.addGroup(item);
    }

    @Override
    public void update(Group item1, Group item2) throws GroupAlreadyExistsException, NoSuchGroupException {
        dao.updateGroup(item1, item2);
    }

    @Override
    public void delete(Group item) throws NoSuchGroupException {
        dao.deleteGroup(item);
    }

    @Override
    public List<Group> getAll() {
        return dao.getGroups();
    }

    @Override
    public List<Group> search(String query) {
        query = query.replace("?", ".?").replace("*", ".*");

        List<Group> result = new ArrayList<>();
        for(Group g : dao.getGroups()){
            if(g.getDepartment().matches(query)) result.add(g);
        }
        return result;
    }

    @Override
    public void save(String file) throws IncorrectFileException{
        dao.save(file);
    }

    @Override
    public void addAll(String file) throws IncorrectFileException {
        dao.addAll(file);
    }
}
