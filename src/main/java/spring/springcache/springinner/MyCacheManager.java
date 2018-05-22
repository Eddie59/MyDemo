package spring.springcache.springinner;

import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * MyCacheManager class
 *
 * @author Administrator
 * @date
 */
public class MyCacheManager extends AbstractCacheManager {
    private Collection<? extends MyCache> caches;

    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends MyCache> loadCaches() {
        return this.caches;
    }
}
