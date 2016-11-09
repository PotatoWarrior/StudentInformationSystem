package application.model.entity;

import application.constants.ModelConstants;
import application.constants.XMLTags;
import application.model.validator.Validator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement(name = XMLTags.STUDENT_ELEMENT)
public class Student {
    private String name;
    private Group group;
    private Date enrollmentDate;

    public Student() {}
    public Student(String name, Group group, Date date) {
        setName(name);
        setGroup(group);
        setEnrollmentDate(date);
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setGroup(Group group) {
        this.group = group;
    }

    @XmlElement
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Student)) return false;
        Student s = (Student)obj;
        return this.name.equals(s.name);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = Validator.getDateFormat();
        return ModelConstants.STUDENT + " " + this.name + ", " + ModelConstants.ENROLLMENT_DATE + ": " + dateFormat.format(this.enrollmentDate) + ", " + this.group;
    }
}
