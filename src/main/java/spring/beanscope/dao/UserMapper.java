package spring.beanscope.dao;

import spring.beanscope.model.User;

import java.util.List;

/**
 * UserMapper class
 *
 * @author Administrator
 * @date
 */
public interface UserMapper {
    List<User> getUser();
}
