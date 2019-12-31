package com.oliver.service.impl;

import com.oliver.entity.Goods;
import com.oliver.service.IRedisGoodsService;
import com.oliver.service.IRedisLoadData;
import com.oliver.service.IRedisLockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * com.oliver.service.impl.RedisLoadDataImpl
 *
 * @author oliver
 * @date 2019/12/19 11:47
 */
@Service
public class RedisLoadDataImpl implements IRedisGoodsService {

    @Resource
    private IRedisLockService<Goods> lockService;

    private static final String GOODS_HASH_KEY = "goods_miaosha_num";

    private static final String GOODS_KEY_PRE = "goods_";

    private static final Long LOCK_SUCCESS = 1L;

    private static final String GOODS_LOOK_PRE = "goods_look_";

    private static final String MINUS_GOODSNUM = "local temp = redis.call('get',KEYS[1]) " +
            "if tonumber(temp)>=tonumber(ARGV[1]) then " +
            "if redis.call('del',KEYS[1])==1 then " +
            "if redis.call('setNX',KEYS[1],tonumber(temp)-tonumber(ARGV[1]))==1 then " +
            "return 1 " +
            "else return 0 end " +
            "return 0 end " +
            "else " +
            "return 0 end";

    private static final String NEW_MINUS_GOODS_NUM = "if redis.call('HEXISTS',KEYS[1],KEYS[2])==1 then " +
            "local temp = redis.call('HGET',KEYS[1],KEYS[2]) " +
            "if tonumber(temp) >= tonumber(ARGV[1]) then " +
            "if redis.call('HDEL',KEYS[1],KEYS[2])==1 then " +
            "return redis.call('HSET',KEYS[1],KEYS[2],tonumber(temp) -tonumber(ARGV[1])) == 1 " +
            "else return 0 end " +
            "else return 0 end " +
            "else return 0 end ";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void loadDataToRedis(List<Goods> data) {
    }


    @Override
    public int setValue(String key, String value) {
        redisTemplate.opsForHash().put(GOODS_HASH_KEY, GOODS_KEY_PRE + key, value);
        Boolean hasKey = redisTemplate.opsForHash().hasKey(GOODS_HASH_KEY, GOODS_KEY_PRE + value);
        if (hasKey) {
            return 1;
        }
        return 0;
    }


    @Override
    public boolean minusGoodsNum(String userId, int goodsId, int minusNum, String expireTime) {
        String threadId = userId + Thread.currentThread().getId();
        String lookKey = GOODS_LOOK_PRE + goodsId;
        boolean lock = lockService.tryGetDistributedLock(lookKey, threadId, expireTime);
        if (lock) {
            String valueOf = String.valueOf(minusNum);
            byte[] goodsKey = (GOODS_KEY_PRE + goodsId).getBytes(StandardCharsets.UTF_8);
            Long result = redisTemplate.execute((RedisCallback<Long>) connection ->
                    connection.eval(NEW_MINUS_GOODS_NUM.getBytes(StandardCharsets.UTF_8)
                            , ReturnType.INTEGER
                            , 2
                            , GOODS_HASH_KEY.getBytes(StandardCharsets.UTF_8)
                            , goodsKey
                            , valueOf.getBytes(StandardCharsets.UTF_8)));
            lockService.unLockDistributedLock(lookKey, threadId);
            return LOCK_SUCCESS.equals(result);
        }
        return false;
    }
}
