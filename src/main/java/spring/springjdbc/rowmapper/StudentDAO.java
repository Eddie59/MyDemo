package spring.springjdbc.rowmapper;

import java.util.List;

public interface StudentDAO {

    List<Student> listStudent();

    Student getStudent(Integer id);

    void create(String name, Integer age);
}
