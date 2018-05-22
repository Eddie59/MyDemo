package spring.springjdbc;

import spring.springjdbc.rdbms.InsertUser;
import spring.springjdbc.rdbms.User;
import spring.springjdbc.rdbms.UserGenericSqlQuery;
import spring.springjdbc.rdbms.UserMappingSqlQuery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RdbmsTest class
 *
 * @author Administrator
 * @date
 */
public class RdbmsTest {
    private static JdbcTemplate template;

    @Before
    public void init() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/myspring?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
        String pwd = "123456";
        String name = "root";

        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, name, pwd);
        dataSource.setDriverClassName(driver);
        template = new JdbcTemplate(dataSource);
    }

    @Test
    public void testGenericSqlQuery() {
        try {
            SqlQuery<User> sqlQuery = new UserGenericSqlQuery(template);
            List<User> users = sqlQuery.execute();
            System.out.println(users.size());
        } catch (Exception exp) {
        }
    }

    @Test
    public void testMappingSqlQuery() {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("name", "eddie");

            SqlQuery<User> sqlQuery = new UserMappingSqlQuery(template);
            User user = sqlQuery.findObjectByNamedParam(paramMap);

            System.out.println(user.getMyName());
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }

    @Test
    public void testSqlFunction() {
        String cntSql = "select count(1) from user";
        SqlFunction<Integer> function = new SqlFunction<>(template.getDataSource(), cntSql);
        int cnt = function.run();

        System.out.println(cnt);


        String selectSql="select myName from user where myName=?";
        SqlFunction<String> func=new SqlFunction<>(template.getDataSource(),selectSql);
        func.declareParameter(new SqlParameter(Types.VARCHAR));
        String name=(String)func.runGeneric(new Object[]{"eddie"});

        System.out.println(name);
    }


    @Test
    public void testSqlUpdate()
    {
        String updateSql = "update user set myName=? where myName=?";
        SqlUpdate update=new SqlUpdate(template.getDataSource(),updateSql,new int[]{Types.VARCHAR, Types.VARCHAR});
        update.update("eddieabc","eddie");


        String delSql="delete from user where myName=:name";
        SqlUpdate del=new SqlUpdate(template.getDataSource(),delSql,new int[]{Types.VARCHAR});
        Map<String,Object> paraMap=new HashMap<>();
        paraMap.put("name","eddieabc");
        del.updateByNamedParam(paraMap);


        SqlUpdate sqlUpdate=new InsertUser(template);
        sqlUpdate.update("username");


    }


}
