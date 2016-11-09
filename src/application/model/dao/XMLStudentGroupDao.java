package application.model.dao;


import application.constants.XMLTags;
import application.exceptions.*;
import application.model.entity.Group;
import application.model.entity.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@XmlRootElement(name = XMLTags.ROOT_ELEMENT)
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLStudentGroupDao implements StudentGroupDao{
    @XmlElement(name = XMLTags.STUDENT_ELEMENT)
    private List<Student> students;

    @XmlElement(name = XMLTags.GROUP_ELEMENT)
    private List<Group> groups;

    private static XMLStudentGroupDao instance;
    private XMLStudentGroupDao(){
        students = new ArrayList<>();
        groups = new ArrayList<>();
    }
    public static XMLStudentGroupDao getInstance(){
        if(instance == null){
            instance = new XMLStudentGroupDao();
        }
        return instance;
    }
    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException {
        if(this.students.contains(student)) throw new StudentAlreadyExistsException();
        this.students.add(student);
        if(!this.groups.contains(student.getGroup())) groups.add(student.getGroup());
    }

    @Override
    public void deleteStudent(Student student) throws NoSuchStudentException {
        if(!this.students.remove(student)) throw new NoSuchStudentException();
    }

    @Override
    public void updateStudent(Student oldStudent, Student newStudent) throws NoSuchStudentException, StudentAlreadyExistsException {
        if(!this.students.remove(oldStudent)) throw new NoSuchStudentException();
        if(this.students.contains(newStudent)) throw new StudentAlreadyExistsException();
        this.students.add(newStudent);
        if(!this.groups.contains(newStudent.getGroup())) this.groups.add(newStudent.getGroup());
    }

    @Override
    public List<Student> getStudents() {
        return this.students;
    }

    @Override
    public void addGroup(Group group) throws GroupAlreadyExistsException {
        if(this.groups.contains(group)) throw new GroupAlreadyExistsException();
        this.groups.add(group);
    }

    @Override
    public void deleteGroup(Group group) throws NoSuchGroupException {
        if (!this.groups.remove(group)) throw new NoSuchGroupException();
        Iterator<Student> iterator = this.students.iterator();
        while(iterator.hasNext()){
            Student s = iterator.next();
            if(s.getGroup().equals(group))
                iterator.remove();
        }
    }
    @Override
    public void updateGroup(Group oldGroup, Group newGroup) throws NoSuchGroupException, GroupAlreadyExistsException {
        if(!this.groups.remove(oldGroup)) throw new NoSuchGroupException();
        if(this.groups.contains(newGroup)) throw new GroupAlreadyExistsException();
        this.groups.add(newGroup);
        this.students.stream().filter(s -> s.getGroup().equals(oldGroup)).forEach(s -> s.setGroup(newGroup));
    }

    @Override
    public List<Group> getGroups() {
        return this.groups;
    }

    @Override
    public void addAll(String file) throws IncorrectFileException {
        try(InputStream inputStream = new GZIPInputStream(new FileInputStream(file))) {
            JAXBContext context = JAXBContext.newInstance(XMLStudentGroupDao.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLStudentGroupDao dao = (XMLStudentGroupDao) unmarshaller.unmarshal(inputStream);

            for(Student s : dao.getStudents())
                try {
                    addStudent(s);
                } catch (StudentAlreadyExistsException e) { }
            for(Group g : dao.getGroups())
                try {
                    addGroup(g);
                } catch (GroupAlreadyExistsException e) { }
        } catch (JAXBException | IOException e) {
            throw new IncorrectFileException();
        }
    }

    @Override
    public void save(String file) throws IncorrectFileException{
        try(OutputStream outputStream = new GZIPOutputStream(new FileOutputStream(file))) {
            JAXBContext context = JAXBContext.newInstance(XMLStudentGroupDao.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, outputStream);
        } catch (JAXBException | IOException e) {
            throw new IncorrectFileException();
        }
    }
}
