package spring.springjdbc;

import spring.springjdbc.rowmapper.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * JdbcTemplateTest class
 *
 * @author Administrator
 * @date
 */
public class JdbcTemplateTest {

    private static JdbcTemplate template;

    @Before
    public void init() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/myspring?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
        String name = "root";
        String pwd = "123456";

        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, name, pwd);
        dataSource.setDriverClassName(driver);
        template = new JdbcTemplate(dataSource);
    }

    @Test
    public void test() {
        List<Student> students = new ArrayList<>();

        String sql = "select * from student";
        template.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                //不明白，为啥写while(resultSet.next())第一条数据检索不到
                do {
                    Student student = new Student();
                    student.setName(resultSet.getString(2));
                    student.setAge(resultSet.getInt(3));
                    students.add(student);
                }
                while (resultSet.next());
            }
        });
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    @Test
    public void testPpreparedStatement1() {
        template.execute(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement("select count(*) from student");
                    }
                },
                new PreparedStatementCallback<Integer>() {
                    @Override
                    public Integer doInPreparedStatement(PreparedStatement pstmt)
                            throws SQLException, DataAccessException {
                        pstmt.execute();
                        ResultSet set = pstmt.getResultSet();
                        //resultset默认指向第一条数据之前，也就是指向没有数据，所以这里要移动到第一条数据
                        set.next();
                        int test = set.getInt(1);
                        return set.getInt(1);
                    }
                }
        );
    }

    /**
     * 预编译语句,设值
     */
    @Test
    public void testPreparedStatement2() {
        String insertSql = "insert into student(name,age) values(?,?)";
        //方式一
        template.update(insertSql, "eddie", 28);
        //方式二
        int cnt = template.update(insertSql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement) throws SQLException {
                statement.setString(1, "ha");
                statement.setInt(2, 20);
            }
        });
    }


    /**
     * 查询结果转化对象
     */
    @Test
    public void testResultSet1() {
        String sql = "select * from student";
        List<Student> students = template.query(sql, new RowMapper<Student>() {
            //mapRow方法中的resultset不需要手动遍历，因为调用时已经使得set.next移动指针了
            @Override
            public Student mapRow(ResultSet set, int i) {
                try {
                    Student student = new Student();
                    student.setAge(set.getInt(3));
                    student.setName(set.getString(2));
                    return student;
                } catch (Exception exp) {
                    return null;
                }
            }
        });
        System.out.println(students.size());
    }


    @Test
    public void testResultSet3() {
        String sql = "select * from student";
        List<Student> data = template.query(sql, new ResultSetExtractor<List<Student>>() {
            @Override
            public List<Student> extractData(ResultSet rs)
                    throws SQLException, DataAccessException {
                List<Student> students = new ArrayList<>();
                while (rs.next()) {
                    Student student = new Student();
                    student.setName(rs.getString(2));
                    student.setAge(rs.getInt(3));
                    students.add(student);
                }
                return students;
            }
        });

        System.out.println(data.size());
    }


    @Test
    public void QueryForTest1() {
        List<Map<String, Object>> data1 = template.queryForList("select * from student");
        for (Map<String, Object> temp : data1) {
            for (String key : temp.keySet()) {
                String val = temp.get(key).toString();
                System.out.println(String.format("key:%s  value:%s", key, val));
            }
        }


    }


}
