package spring.springjdbc.rowmapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    private ApplicationContext context;

    @Before
    public void init() {
        String[] configs = new String[]{"classpath:datasource.xml", "classpath:springjdbc/rowmapper.xml"};
        context = new ClassPathXmlApplicationContext(configs);
    }

    @Test
    public void run() {
        StudentImpl impl = context.getBean("studentImpl", StudentImpl.class);

        impl.create("eddie",28);
        Student student= impl.getStudent(1);
        List<Student> students = impl.listStudent();

    }
}
