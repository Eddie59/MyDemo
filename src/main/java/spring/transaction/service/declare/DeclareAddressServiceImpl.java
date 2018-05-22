package spring.transaction.service.declare;

import spring.transaction.dao.IAddressDao;
import spring.transaction.model.AddressModel;
import spring.transaction.service.IAddressService;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * DeclareAddressServiceImpl class
 *
 * @author Administrator
 * @date
 */
public class DeclareAddressServiceImpl implements IAddressService {
    private IAddressDao addressDao;
    private PlatformTransactionManager txmanager;

    public void setTxmanager(PlatformTransactionManager txmanager) {
        this.txmanager = txmanager;
    }

    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public int countAll() {
        return addressDao.countAll();
    }

    @Override
    public void save(AddressModel address) {
        addressDao.save(address);
    }
}
