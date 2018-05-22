package spring.transaction.dao;

import spring.transaction.model.AddressModel;

public interface IAddressDao {
    void save(AddressModel address);

    int countAll();
}
