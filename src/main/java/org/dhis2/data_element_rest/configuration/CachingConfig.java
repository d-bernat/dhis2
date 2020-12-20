package org.dhis2.data_element_rest.configuration;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfig
{

    @Value("${dhis2.dataElements.cache.ttl-in-minutes}")
    private Long dataElementsCacheTTL;

    @Value("${dhis2.dataElementGroups.cache.ttl-in-minutes}")
    private Long dataElementGroupsCacheTTL;

    @Bean
    public CacheManager cacheManager()
    {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("dataElements", CacheBuilder.newBuilder()
                                                                       .expireAfterWrite(dataElementsCacheTTL, TimeUnit.MINUTES)
                                                                       .maximumSize(1)
                                                                       .build()
                                                                       .asMap(), false),
                new ConcurrentMapCache("dataElementGroups", CacheBuilder.newBuilder()
                                                                        .expireAfterWrite(dataElementGroupsCacheTTL, TimeUnit.MINUTES)
                                                                        .maximumSize(1)
                                                                        .build()
                                                                        .asMap(), false)));
        return cacheManager;
    }
}
