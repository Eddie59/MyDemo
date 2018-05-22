package spring.beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.beanscope.dao.UserInfoMapper;
import spring.beanscope.dao.UserMapper;


/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    public static void main(String...args)
    {
        ApplicationContext app=new ClassPathXmlApplicationContext("spring/beanscope/beanscope.xml");

        UserMapper userMapper= app.getBean("userMapper",UserMapper.class);
        UserInfoMapper userInfoMapper=app.getBean("userInfoMapper",UserInfoMapper.class);

    }
}
