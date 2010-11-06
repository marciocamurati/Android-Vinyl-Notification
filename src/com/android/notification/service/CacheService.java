package com.android.notification.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * Google Memcache interface
 * 
 * @author marciocamurati
 *
 */
@Service
public class CacheService {
	/*
	 * Log4j
	 */
	private static final Logger log = Logger.getLogger(CacheService.class.getName());

	public static MemcacheService cache = null;
	
	static	{
		try {
			cache = MemcacheServiceFactory.getMemcacheService();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setValue(String key, byte[] value)	{
		return setValue(key, value, null);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expiration
	 * @return
	 */
	public Boolean setValue(String key, Object value, Expiration expiration)	{
		Boolean add = false;
		
		try {
			if (expiration != null)
				cache.put(key, value, expiration);
			else
				cache.put(key, value);
			
			add = true;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return add;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object getValue(String key) throws Exception	{
		if (key == null)
			throw new Exception("Invalid null key");
		
		byte[] cacheValue = (byte[]) cache.get(key);
		
		if (cacheValue != null)
			return cacheValue;
		
		return null;
	}

}
