package spring.transaction.service.code;

import spring.transaction.dao.IUserDao;
import spring.transaction.model.UserModel;
import spring.transaction.service.IUserService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * CodeUserServiceImpl class
 *
 * @author Administrator
 * @date
 */
public class CodeUserServiceImpl implements IUserService {
    private IUserDao userDao;
    private PlatformTransactionManager txManager;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(UserModel user) {
        TransactionTemplate template = new TransactionTemplate(txManager);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        template.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userDao.save(user);
                    userDao.save(user);
//                    throw new Exception("error");
                } catch (Exception exp) {
                    System.out.println(exp);
                    status.setRollbackOnly();
                }
            }
        });
    }

        @Override
        public int countAll () {
            return 0;
        }
    }
