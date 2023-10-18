package com.social.profile.config.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
@ConditionalOnProperty(name = "cache.simple.enable", havingValue = "true")
@ConditionalOnMissingBean(name = "cacheManager")
public class SimpleSpringCache
{

	@Bean
	@Primary
	public CacheManager cacheManager()
	{
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> caches = new ArrayList<>();
		caches.add(new ConcurrentMapCache("data"));
		cacheManager.setCaches(caches);
		System.out.println("...####Simple cache Bean is created####....");
		return cacheManager;
	}

	//*This bean will not be created because of same type*/
	@Bean(name = "cacheManager1")
	@ConditionalOnBean(name = "cacheManager")
	public CacheManager cacheManager1()
	{
		System.out.println("...####If cacheManager bean is present than creating cacheManager1####....");
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		return cacheManager;
	}

}
