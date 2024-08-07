package com.social.user.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import com.social.domain.Country;
import com.social.presentation.ProfileDTO;

@Component
/*
 *  cache configuration into a single place at the class level
 */
@CacheConfig(cacheManager = "cacheManager", cacheNames = "data")
public class CacheService
{
	
	@Autowired
	CacheManager cacheManager;

	@Cacheable(key = "'person'")
	public ProfileDTO addPersonToSpringCache()
	{
		return new ProfileDTO();
	}

	/*
	 * updating cache
	 * */
	@Cacheable(key = "'person-1'")
	public ProfileDTO addPersonToRedisCache()
	{
		return new ProfileDTO();
	}

	/* when we need both annotations @CachePut or @CacheEvict
	    at the same time on the same method
	 */
	@Caching(evict =
	{
		@CacheEvict("country"),
		@CacheEvict(value = "country", key = "#student.id")
	}, put = @CachePut(cacheNames = "countries", key = "#countryName"))
	public Country[] getAddress(Country country)
	{
		return Country.values();
	}

	/* Cache Evict is used when we want to remove stale or unused data
	 from the cache.
	  Cache Evict is a trigger also used on void methods
	 */
	@CacheEvict(cacheNames =
	{"data"}, allEntries = true) // removing all entries from the cache
	public String removeAllCache()
	{
		return null;
	}

	/*@Cacheable annotation skips the method execution if cache is present already
	 while the @CachePut annotation runs the method
	 and put the result into the cache.
	 */
	@CachePut(key = "'person-1'") // updating cache
	public ProfileDTO updatePerson(String email)
	{
		return new ProfileDTO();
	}

	public void clearRedisCache()
	{
		cacheManager.getCacheNames()
			.parallelStream()
			.forEach(n -> cacheManager.getCache(n).clear());
	}
}
