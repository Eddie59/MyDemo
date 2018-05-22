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
        String[] config = new String[]{"classpath:transaction/transaction.xml","transaction/transaction-code.xml"};
        ctx = new ClassPathXmlApplicationContext(config);
        txManager = ctx.getBean(PlatformTransactionManager.class);
        dataSource = ctx.getBean(DataSource.class);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Test
    public void testPlatformTransactionManagerForLowLevel1() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        Connection conn = DataSourceUtils.getConnection(dataSource);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_TABLE_SQL);
            preparedStatement.execute();

            PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1, "test");
            pstmt.execute();

            conn.prepareStatement(DROP_TABLE_SQL).execute();

            txManager.commit(status);
        } catch (Exception exp) {
            status.setRollbackOnly();
            txManager.rollback(status);
        }
    }


    @Test
    public void testPlatformTransactionManagerForHighLevel() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        jdbcTemplate.execute(CREATE_TABLE_SQL);
        try {
            jdbcTemplate.update(INSERT_SQL, "test");
            txManager.commit(status);
        } catch (RuntimeException e) {
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
