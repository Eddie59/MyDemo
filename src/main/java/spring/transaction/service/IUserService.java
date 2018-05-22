package spring.transaction.service;

import spring.transaction.model.UserModel;

/**
 * IUserService class
 *
 * @author Administrator
 * @date
 */
public interface IUserService {
    void save(UserModel user);

    int countAll();
}
