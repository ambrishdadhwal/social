package com.social.profile.controllers;

import com.social.presentation.ProfileDTO;
import com.social.profile.config.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CacheService personCacheService;

    @Autowired
    CacheManager cacheManager;

    @GetMapping(value = "/fill-spring-cache")
    public ResponseEntity<?> addPersonToSpringCache()
    {
        return new ResponseEntity<>(personCacheService.addPersonToSpringCache(), HttpStatus.OK);
    }

    @GetMapping(value = "/fill-redis-cache")
    public ResponseEntity<?> cacheCountries()
    {
        return new ResponseEntity<>(personCacheService.addPersonToRedisCache(), HttpStatus.OK);
    }

    @PutMapping(value = "/update-cache/{email}")
    public ResponseEntity<?> updateCache(@PathVariable String email)
    {
        return new ResponseEntity<>(personCacheService.updatePerson(email), HttpStatus.OK);
    }

    @GetMapping(value = "/get-cache/{cacheName}")
    public ResponseEntity<?> getCacheCountries(@PathVariable(name = "cacheName", required = false) String cacheName)
    {
        Collection<String> caches = cacheManager.getCacheNames();
        if(cacheName!=null)
        {
            if(cacheManager instanceof RedisCacheManager)
            {
                RedisCacheManager redisCacheManager = (RedisCacheManager)cacheManager;
                redisCacheManager.getCache(cacheName);
                return new ResponseEntity<>(redisCacheManager.getCache(cacheName), HttpStatus.OK);
            }
            else
            {
                ConcurrentMapCache scm = (ConcurrentMapCache)cacheManager.getCache("data");
                SimpleValueWrapper clientCache = (SimpleValueWrapper)scm.get(cacheName);
                return new ResponseEntity<>(clientCache.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(caches, HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<?> deleteAllCache()
    {
        personCacheService.removeAllCache();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get-cache")
    public ResponseEntity<?> getAllCache()
    {
        Collection<String> caches = cacheManager.getCacheNames();
        return new ResponseEntity<>(caches, HttpStatus.OK);
    }
}
