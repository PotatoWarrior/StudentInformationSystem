package application.model;


import application.model.entity.Group;

import java.util.List;

public interface GroupModel extends Model<Group>{
    List<Group> searchByDepartment(String query);
}
