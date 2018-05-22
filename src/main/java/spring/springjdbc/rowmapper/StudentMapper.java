package spring.springjdbc.rowmapper;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StudentMapper class
 *
 * @author Administrator
 * @date
 */
public class StudentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setAge(resultSet.getInt("age"));
        student.setName(resultSet.getString("name"));
        student.setId(resultSet.getInt("id"));
        return student;
    }
}
