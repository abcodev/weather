package com.web.weather.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisCacheService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public <T> T getFromCache(String key, Class<T> type) {
    return (T) redisTemplate.opsForValue().get(key);
  }

  public <T> void saveToCache(String key, T value, long ttl, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, ttl, unit);
  }

  public void deleteFromCache(String key) {
    redisTemplate.delete(key);
  }
}
