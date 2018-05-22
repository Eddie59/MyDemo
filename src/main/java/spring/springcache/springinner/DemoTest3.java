package spring.springcache.springinner;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * DemoTest3 class
 *
 * @author Administrator
 * @date
 */
public class DemoTest3 {
    private AccountService3 accountService3;
    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/springcache/springcache.xml");
        accountService3 = context.getBean("accountService3", AccountService3.class);
    }
    @Test
    public void testInject(){
        assertNotNull(accountService3);
    }
    @Test
    public void testGetAccountByName() throws Exception {
        Account account = accountService3.getAccountByName("someone");
        account = accountService3.getAccountByName("someone");
    }
}
