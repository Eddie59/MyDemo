package spring.springjdbc.rowmapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * StudentImpl class
 *
 * @author Administrator
 * @date
 */
public class StudentImpl implements StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Student> listStudent() {
        List<Student> students = jdbcTemplate.query("select * from student", new StudentMapper());
        return students;
    }

    @Override
    public Student getStudent(Integer id) {
        List<Student> students = jdbcTemplate.query("select * from student where id=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new StudentMapper());
        return students.get(0);
    }

    @Override
    public void create(String name, Integer age) {
        String insertSql = "insert into student(name,age) values(?,?)";
        jdbcTemplate.update(insertSql, name, age);
    }
}
