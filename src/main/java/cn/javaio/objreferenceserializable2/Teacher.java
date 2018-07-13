package cn.javaio.objreferenceserializable2;

import java.io.Serializable;

/**
 * Teacher class
 *
 * @author Administrator
 * @date
 */
public class Teacher implements Serializable {
    private String name;
    private Person student;

    public Teacher(String _name, Person _student) {
        this.name = _name;
        this.student = _student;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }
}
