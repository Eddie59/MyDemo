package spring.transaction.service.code;

import spring.transaction.dao.IAddressDao;
import spring.transaction.model.AddressModel;
import spring.transaction.service.IAddressService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * CodeAddressServiceImpl class
 *
 * @author Administrator
 * @date
 */
public class CodeAddressServiceImpl implements IAddressService {
    private IAddressDao addressDao;
    private PlatformTransactionManager txmanager;

    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setTxmanager(PlatformTransactionManager txmanager) {
        this.txmanager = txmanager;
    }


    @Override
    public int countAll() {
        return addressDao.countAll();
    }

    @Override
    public void save(AddressModel address) {
//        addressDao.save(address);
        TransactionTemplate transactionTemplate = new TransactionTemplate(txmanager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    addressDao.save(address);
                    addressDao.save(address);
//                    throw new Exception("error");
                } catch (Exception exp) {
                    System.out.println(exp);
                    status.setRollbackOnly();
                }
            }
        });

    }
}
