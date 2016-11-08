package application.model;

import application.constants.ModelConstants;
import application.constants.XMLTags;
import application.model.validator.Validator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = XMLTags.GROUP_ELEMENT)
public class Group {
    private int number;
    private String department;

    public Group(){}

    public Group(int number, String department) {
        setNumber(number);
        setDepartment(department);
    }

    @XmlElement
    public void setNumber(int number) {
        if(!Validator.validateGroupNumber(number)) throw new IllegalArgumentException(ModelConstants.NUMBER + " = " + number);
        this.number = number;
    }

    @XmlElement
    public void setDepartment(String department) {
        if(department == null) throw new NullPointerException();
        if(!Validator.validateGroupDepartment(department)) throw new IllegalArgumentException(ModelConstants.DEPARTMENT + " = " + department);
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
        return ModelConstants.GROUP + " " + this.number + ", " + ModelConstants.DEPARTMENT + ": " + this.department;
    }
}
