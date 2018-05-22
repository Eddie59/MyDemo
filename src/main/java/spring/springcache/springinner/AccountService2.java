package spring.springcache.springinner;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AccountService2 class
 *
 * @author Adminis @date
 */
@Service
public class AccountService2 {

    @Cacheable("accountCache")
    public Account getAccountByName(String accountName) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        Optional<Account> accountOptional = getFromDB(accountName);
        return accountOptional.get();
    }

        //getFromDB
    private Optional<Account> getFromDB(String accountName) {
        return Optional.ofNullable(new Account(accountName));
    }
}
