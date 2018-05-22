package spring.springcache.springinner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AccountService1 class
 *
 * @author Administrator
 * @date
 */
@Service
public class AccountService1 {
    @Autowired
    private CacheContext<Account> accountCacheContext;

    //getAccountByName
    public Account getAccountByName(String accountName) {
        Account result = accountCacheContext.get(accountName);
        if (result != null) {
            return result;
        }
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }
        Account account = accountOptional.get();
        accountCacheContext.addOrUpdateCache(accountName, account);
        return account;
    }

    //reload
    public void reload() {
        accountCacheContext.evictCache();
    }

    //getFromDB
    private Optional<Account> getFromDB(String accountName) {
        return Optional.ofNullable(new Account(accountName));
    }
}
