package spring.transaction.service.annotation;

import spring.transaction.dao.IAddressDao;
import spring.transaction.model.AddressModel;
import spring.transaction.service.IAddressService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * AnnotationAddressServiceImpl class
 *
 * @author Administrator
 * @date
 */

public class AnnotationAddressServiceImpl implements IAddressService {
    private IAddressDao addressDao;

    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /**
     * 如果Service层会抛出不属于运行时异常也要能回滚，那么可以将Spring默认的回滚时的异常修改为Exception，这样就可以保证碰到什么异常都可以回滚
     * @param address
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = Exception.class)
    @Override
    public void save(AddressModel address) {
     /*   //这样写，发生异常，catch捕获异常，没有抛出异常，事务是不会回滚的，数据库有一条数据
        try {
            address.setProvince("sh");
            addressDao.save(address);

            address.setProvince(null);
            addressDao.save(address);
        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }*/

        try {
            address.setProvince("sh");
            addressDao.save(address);

            address.setProvince(null);
            addressDao.save(address);
        }
        catch (Exception exp)
        {
            System.out.println(exp);
            throw exp;
        }
    }

    @Override
    public int countAll() {
        return addressDao.countAll();
    }
}
