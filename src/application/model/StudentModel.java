package application.model;


import application.model.entity.Student;

import java.util.List;

public interface StudentModel extends Model<Student>{
    List<Student> searchByName(String query);
    List<Student> searchByGroup(String numberQuery, String departmentQuery);
    List<Student> searchByDepartment(String query);
    List<Student> searchByDate(String dateQuery);
}
