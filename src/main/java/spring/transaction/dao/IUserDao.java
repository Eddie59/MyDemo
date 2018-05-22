package spring.transaction.dao;

import spring.transaction.model.UserModel;

public interface IUserDao {
    void save(UserModel user);

    int countAll();
}
