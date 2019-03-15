package spring.transaction;

import spring.transaction.model.AddressModel;
import spring.transaction.service.IAddressService;
import spring.transaction.service.code.CodeAddressServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * CodeTransactionTest class
 *
 * @author Administrator
 * @date
 */
public class CodeTransactionTest {
    private static ApplicationContext ctx;
    private static PlatformTransactionManager txManager;
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;


    private static final String CREATE_TABLE_SQL = "create table test" +
            "(id int auto_increment PRIMARY KEY, " +
            "name varchar(100))";
    private static final String DROP_TABLE_SQL = "drop table test";
    private static final String CREATE_USER_TABLE_SQL = "create table user" +
            "(id int  PRIMARY KEY, " +
            "name varchar(100))";

    private static final String DROP_USER_TABLE_SQL = "drop table user";
    private static final String CREATE_ADDRESS_TABLE_SQL = "create table address" +
            "(id int auto_increment PRIMARY KEY, " +
            "province varchar(100), city varchar(100), street varchar(100), user_id int)";

    private static final String DROP_ADDRESS_TABLE_SQL = "drop table address";
    private static final String INSERT_SQL = "insert into test(id) values(?)";
    private static final String COUNT_SQL = "select count(*) from test";


    @Before
    public void init() {
        String[] config = new String[]{"classpath:spring/transaction/transaction.xml","classpath:spring/transaction/transaction-code.xml"};

        ctx = new ClassPathXmlApplicationContext(config);
        txManager = ctx.getBean(PlatformTransactionManager.class);
        dataSource = ctx.getBean(DataSource.class);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 使用低级别方案JdbcTemplate来进行事务管理器测试
     */
    @Test
    public void testPlatformTransactionManagerForLowLevel1() {
        //定义一个事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //事务隔离级别
        def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        //支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择，也是 Spring 默认的事务的传播
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //返回一个已经激活的事务或创建一个新的事务（根据给定的TransactionDefinition类型参数定义的事务属性）
        TransactionStatus status = txManager.getTransaction(def);

        try {
            Connection conn = DataSourceUtils.getConnection(dataSource);
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_TABLE_SQL);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, "test");
            preparedStatement.execute();

            conn.prepareStatement(DROP_TABLE_SQL).execute();

            txManager.commit(status);
        } catch (Exception exp) {
            System.out.println(exp);
            status.setRollbackOnly();
            txManager.rollback(status);
        }
    }


    /**
     * 使用高级别方案JdbcTemplate来进行事务管理器测试
     */
    @Test
    public void testPlatformTransactionManagerForHighLevel() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        try {
            jdbcTemplate.execute(CREATE_TABLE_SQL);
            jdbcTemplate.update(INSERT_SQL, "test");
            txManager.commit(status);
        } catch (RuntimeException e) {
            System.out.println(e);
            txManager.rollback(status);
        }
        jdbcTemplate.execute(DROP_TABLE_SQL);
    }


    @Test
    public void testTransactionTemplate() {
        jdbcTemplate.execute(CREATE_TABLE_SQL);

        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    jdbcTemplate.update(INSERT_SQL, "test");
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
            }
        });

        jdbcTemplate.execute(DROP_TABLE_SQL);
    }


    /**
     * 真实项目中使用编程式事务
     */
    @Test
    public void CodeTrunsaction() {
        AddressModel addressModel = new AddressModel();
        addressModel.setCity("sh");
        addressModel.setProvince("sh");
        addressModel.setStreet("宛平南路");
        addressModel.setUserId(1);

        IAddressService addressService = ctx.getBean("addressService", CodeAddressServiceImpl.class);
        addressService.save(addressModel);
        addressService.countAll();
    }


}
