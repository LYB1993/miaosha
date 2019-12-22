package com.oliver.service.impl;

import com.oliver.entity.Goods;
import com.oliver.service.IRedisService;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * com.oliver.service.impl.RedisServiceImpl
 *
 * @author oliver
 * @date 2019/12/19 11:06
 */
@Service
public class RedisServiceImpl implements IRedisService<Goods> {

    private static final Long LOCK_SUCCESS = 1L;

    private static final String ADD_LOCK = "if redis.call('setNX',KEYS[1],ARGV[1])==1 then  return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";

    private static final String UN_LOCK = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    private static final String ADD = "return redis.call('setNX',KEYS[1],ARGV[1])==1";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean tryGetDistributedLock(String lockKey, String requestId, String expireTime) {
        Object result = redisTemplate.execute((RedisCallback<Long>) connection -> connection.eval(ADD_LOCK.getBytes(StandardCharsets.UTF_8), ReturnType.INTEGER, 1, lockKey.getBytes(StandardCharsets.UTF_8), requestId.getBytes(StandardCharsets.UTF_8), expireTime.getBytes(StandardCharsets.UTF_8)));
        return LOCK_SUCCESS.equals(result);
    }

    @Override
    public boolean setValue(String key, String value) {
        return LOCK_SUCCESS.equals(redisTemplate.execute(
                (RedisCallback<Long>) connection -> connection
                        .eval(ADD.getBytes(StandardCharsets.UTF_8), ReturnType.INTEGER, 1,
                                key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8))));

    }

    @Override
    public boolean unLockDistributedLock(String lockKey, String requestId) {
        Object result = redisTemplate.execute((RedisCallback<Long>) connection -> connection.eval(UN_LOCK.getBytes(StandardCharsets.UTF_8), ReturnType.INTEGER, 1, lockKey.getBytes(StandardCharsets.UTF_8), requestId.getBytes(StandardCharsets.UTF_8)));
        return LOCK_SUCCESS.equals(result);
    }
}
