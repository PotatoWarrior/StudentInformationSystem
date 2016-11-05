package application.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="group")
public class Student {
    private String name;
    private Group group;
    private String enrollMentDate;

    public Student() {}
    public Student(String name, Group group, String date) {
        this.name = name;
        this.group = group;
        this.enrollMentDate = date;
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
    public void setEnrollMentDate(String enrollMentDate) {
        this.enrollMentDate = enrollMentDate;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public String getEnrollMentDate() {
        return enrollMentDate;
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
        return "Student " + this.name + ", enrollment date: " + this.enrollMentDate + ", " + this.group;
    }
}
