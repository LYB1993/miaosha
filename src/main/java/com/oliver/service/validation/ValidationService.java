package com.oliver.service.validation;

import com.oliver.entity.am.AmSeckill;
import com.oliver.entity.am.AmSeckillDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * com.oliver.service.impl.UserRedisImpl
 *
 * @author oliver
 * @date 2019/12/27 20:11
 */
@Service
public class ValidationService {

    private static final String PREFIX_KEY = "amseckill_";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private AmSeckillDAO baseDao;

    public boolean freq(String loginId) {
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        String value = forValue.get(loginId);
        if (StringUtils.isBlank(value)) {
            forValue.set(loginId, UUID.randomUUID().toString(), 5, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public boolean isStart(long amId) {
        String key = PREFIX_KEY + amId;
        String strStartTimestamp = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(strStartTimestamp)) {
            long startTimestamp = Long.parseLong(strStartTimestamp);
            return new Date().getTime() >= startTimestamp;
        } else {
            AmSeckill amSeckill = baseDao.selectByPrimaryKey(amId);
            return amSeckill != null && new Date().after(amSeckill.getStartDatetime());
        }
    }
}
