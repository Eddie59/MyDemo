package spring.springcache.springinner;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * CacheContext class
 *
 * @author Administrator
 * @date
 */
@Service
public class CacheContext<T> {
    private Map<String, T> cache = new HashMap<>();

    public T get(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T t) {
        cache.put(key, t);
    }

    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }

    }

    public void evictCache() {
        cache.clear();
    }
}
