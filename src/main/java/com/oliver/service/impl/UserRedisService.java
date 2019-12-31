package com.oliver.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * com.oliver.service.impl.UserRedisImpl
 *
 * @author oliver
 * @date 2019/12/27 20:11
 */
@Service
public class UserRedisService {

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public boolean freq(String loginId) {
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        String value = forValue.get(loginId);
        if (StringUtils.isBlank(value)) {
            forValue.set(loginId, UUID.randomUUID().toString(), 5, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }
}
