package spring.springcache.springinner;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * MyCache class
 *
 * @author Administrator
 * @date
 */
public class MyCache implements Cache {
    private String name;
    private Map<String,Account> store = new HashMap<>();;

    public MyCache() {
    }

    public MyCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return store;
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Account thevalue = store.get(key);
        if(thevalue!=null) {
            thevalue.setPassword("from mycache:"+name);
            result = new SimpleValueWrapper(thevalue);
        }
        return result;
    }

    @Override
    public void put(Object key, Object value) {
        Account thevalue = (Account)value;
        store.put((String)key, thevalue);
    }



    @Override
    public void evict(Object key) {
    }

    @Override
    public void clear() {
    }

    @Nullable
    @Override
    public <T> T get(Object o, @Nullable Class<T> aClass) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object o, @Nullable Object o1) {
        return null;
    }
}
