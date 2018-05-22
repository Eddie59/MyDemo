package spring.springjdbc;

import spring.springjdbc.rdbms.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleJdbcTest class
 *
 * @author Administrator
 * @date
 */
public class SimpleJdbcTest {
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
    public void testSimpleJdbcInsert() {
        Map<String, Object> args = new HashMap<>();
        args.put("myName", "eddie");

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.withTableName("user");
        insert.usingColumns("myName");
        insert.compile();

        //1.普通插入
        insert.execute(args);

        //2.插入时获取主键值
        insert = new SimpleJdbcInsert(template);
        insert.withTableName("user");
        insert.usingColumns("myName");
        insert.setGeneratedKeyName("id");
        Number id = insert.executeAndReturnKey(args);
        System.out.println(id);

        //3.批处理
        insert = new SimpleJdbcInsert(template);
        insert.withTableName("user");
        insert.usingColumns("myName");
        insert.setGeneratedKeyName("id");
        int[] insertCnt = insert.executeBatch(new Map[]{args, args, args});
        for (int item : insertCnt) {
            System.out.println(item);
        }
    }


    /**
     * SimpleJdbcCall用来调用存储过程或者自定义函数
     */
    @Test
    public void testSimpleJdbcCall1() {
        SimpleJdbcCall call = new SimpleJdbcCall(template);
        //自定义函数名称
        call.withFunctionName("FUNCTION_TEST");
        call.declareParameters(new SqlOutParameter("result", Types.INTEGER));
        call.declareParameters(new SqlParameter("str", Types.VARCHAR));

        Map<String, Object> outVlaues = call.execute("test");
        System.out.println(outVlaues.get("result"));
        outVlaues = call.execute("test1");
        System.out.println(outVlaues.get("result"));
    }

    @Test
    public void testSimpleJdbcCall2() {
        //调用hsqldb自定义函数得使用如下方式
        SimpleJdbcCall call = new SimpleJdbcCall(template);
        call.withProcedureName("FUNCTION_TEST");
        call.declareParameters(new SqlReturnResultSet("result", new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                while(rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }));
        call.declareParameters(new SqlParameter("str", Types.VARCHAR));
        Map<String, Object> outVlaues = call.execute("test");
        System.out.println( outVlaues.get("result"));
    }

    @Test
    public void testSimpleJdbcCall3() {
        SimpleJdbcCall call = new SimpleJdbcCall(template);
        call.withProcedureName("PROCEDURE_TEST");
        call.declareParameters(new SqlInOutParameter("inOutName", Types.VARCHAR));
        call.declareParameters(new SqlOutParameter("outId", Types.INTEGER));
        SqlParameterSource params = new MapSqlParameterSource().addValue("inOutName", "test");
        Map<String, Object> outVlaues = call.execute(params);

        System.out.println(outVlaues.get("inOutName"));
        System.out.println(outVlaues.get("outId"));
    }





//获取自动生成的主键

    @Test
    public void testFetchKey1() throws SQLException {
        final String insertSql = "insert into user(myName) values('name5')";

        //使用KeyHolder获取添加生成的主键时，一定要指定prepareStatement的列名，主键的列名
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                return conn.prepareStatement(insertSql,new String[]{"id"});
            }
        }, generatedKeyHolder);

        System.out.println(generatedKeyHolder.getKey());
    }




    @Test
    public void testFetchKey2() {
        final String insertSql = "insert into user(name) values('name5')";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        SqlUpdate update = new SqlUpdate();
        update.setJdbcTemplate(template);
        update.setReturnGeneratedKeys(true);
        //update.setGeneratedKeysColumnNames(new String[]{"ID"});
        update.setSql(insertSql);
        update.update(null, generatedKeyHolder);
        System.out.println( generatedKeyHolder.getKey());
    }


    @Test
    public void testBatchUpdate1() {
        String insertSql = "insert into user(myName) values('name5')";
        String[] batchSql = new String[] {insertSql, insertSql};
        template.batchUpdate(batchSql);

    }

    @Test
    public void testBatchUpdate2() {
        String insertSql = "insert into user(myName) values(?)";
        final String[] batchValues = new String[] {"name5", "name6"};
        template.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, batchValues[i]);
            }
            @Override
            public int getBatchSize() {
                return batchValues.length;
            }
        });
    }

    @Test
    public void testBatchUpdate3() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);

        User model = new User();
        model.setMyName("name11");

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(new Object[] {model, model});

        String insertSql = "insert into user(myName) values(:myName)";
        namedParameterJdbcTemplate.batchUpdate(insertSql, params);
    }

    @Test
    public void testBatchUpdate4() {
        String insertSql = "insert into user(myName) values(?)";

        List<Object[]> params = new ArrayList<Object[]>();
        params.add(new Object[]{"name5"});
        params.add(new Object[]{"name5"});

        template.batchUpdate(insertSql, params);
    }

    @Test
    public void testBatchUpdate5() {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.withTableName("user");
        insert.usingColumns("myName");

        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("myName", "name6");

        insert.executeBatch(new Map[] {valueMap, valueMap});
    }




}
