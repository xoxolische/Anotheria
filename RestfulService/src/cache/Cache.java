package cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * Class wrapper for easier use of EHCache library.
 * 
 * @author Nikita Pavlov
 *
 * @param <K>
 *            type of key
 * @param <V>
 *            type of value
 */
public class Cache<K, V> {
	private CacheManager cacheManager;
	private Class<K> key;
	private Class<V> value;
	private String name;

	/**
	 * Constructor calls CacheManagerBuilder, sets parameters, builds and initiates
	 * our cache.
	 * 
	 * @param name
	 *            that is the name of our cache
	 * @param heapSize
	 *            int value of heap size
	 * @param key
	 *            type of our key
	 * @param value
	 *            type of our value
	 */
	public Cache(String name, int heapSize, Class<K> key, Class<V> value) {
		super();
		this.key = key;
		this.value = value;
		this.name = name;
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache(name,
				CacheConfigurationBuilder.newCacheConfigurationBuilder(key, value, ResourcePoolsBuilder.heap(heapSize)))
				.build();
		cacheManager.init();
	}

	/**
	 * 
	 * @return org.ehcache.Cache entity
	 */
	public org.ehcache.Cache<K, V> getCache() {
		return cacheManager.getCache(name, key, value);
	}

	/**
	 * Destroy our cache
	 */
	public void destroy() {
		cacheManager.removeCache(this.name);
		cacheManager.close();
	}

	/**
	 * 
	 * @param key
	 *            of entity
	 * @return value if exists, otherwise null
	 */
	public V get(K key) {
		return cacheManager.getCache(name, this.key, this.value).get(key);
	}

	/**
	 * Add to cache Key and Value
	 * 
	 * @param key
	 *            for our value to associate with
	 * @param value
	 *            to save
	 */
	public void put(K key, V value) {
		cacheManager.getCache(name, this.key, this.value).put(key, value);
	}
}
