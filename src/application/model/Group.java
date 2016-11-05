package application.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="group")
public class Group {
    private int number;
    private String department;

    public Group(){}

    public Group(int number, String fac) {
        this.number = number;
        this.department = fac;
    }

    @XmlElement
    public void setNumber(int number) {
        this.number = number;
    }

    @XmlElement
    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Group)) return false;
        Group g = (Group) obj;
        return (this.number == g.number && this.department.equals(g.department));
    }

    @Override
    public String toString() {
        return "Group " + this.number + ", department: " + this.department;
    }
}
