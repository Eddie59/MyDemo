package spring.transaction.service;

import spring.transaction.model.AddressModel;

/**
 * IAddressService class
 *
 * @author Administrator
 * @date
 */
public interface IAddressService {
    void save(AddressModel address);

    int countAll();
}
