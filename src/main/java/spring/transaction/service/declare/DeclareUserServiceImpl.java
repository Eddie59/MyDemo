package spring.transaction.service.declare;

import spring.transaction.dao.IUserDao;
import spring.transaction.model.UserModel;
import spring.transaction.service.IUserService;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * DeclareUserServiceImpl class
 *
 * @author Administrator
 * @date
 */
public class DeclareUserServiceImpl implements IUserService {
    private IUserDao userDao;
    private PlatformTransactionManager txManager;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public int countAll() {
        return userDao.countAll();
    }

    @Override
    public void save(UserModel user) {
        userDao.save(user);
    }
}
