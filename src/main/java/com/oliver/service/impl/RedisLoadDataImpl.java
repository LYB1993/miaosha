package com.oliver.service.impl;

import com.oliver.entity.Goods;
import com.oliver.service.IRedisLoadData;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * com.oliver.service.impl.RedisLoadDataImpl
 *
 * @author oliver
 * @date 2019/12/19 11:47
 */
@Service
public class RedisLoadDataImpl implements IRedisLoadData<Goods> {

    private static final String GOODS_HASH_KEY = "goods_miaosha_num";
    private static final String GOODS_KEY_PRE = "goods_";

    private static final String LOCK_SUCCESS = "1";

    private static final String LOCK_SUCCESS_STR = "OK";

    private static final String GOODS_LOOK_PRE = "goods_look_";

    private static final String ADD_GOODS_LOCK = "if redis.call('setNX',KEYS[1],ARGV[1])==1 " +
            "then " +
            "return redis.call('expire',KEYS[1],ARGV[2]) " +
            "else " +
            "return 0 end";

    private static final String UN_GOODS_LOCK = "if redis.call('get',KEYS[1])==ARGV[1] " +
            "then " +
            "return redis.call('del',KEYS[1]) " +
            "else " +
            "return 0 end";

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
    public int loadGoodsNum(int goodsId, int goodsNum) {
        redisTemplate.opsForHash().put(GOODS_HASH_KEY, GOODS_KEY_PRE + goodsId, String.valueOf(goodsNum));
        Boolean hasKey = redisTemplate.opsForHash().hasKey(GOODS_HASH_KEY, GOODS_KEY_PRE + goodsId);
        if (hasKey) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getGoodsNum(int goodsId) {
        String goodsNum = redisTemplate.opsForValue().get(GOODS_KEY_PRE + goodsId);
        if (StringUtils.isNotBlank(goodsNum)) {
            return Integer.parseInt(goodsNum);
        }
        return 0;
    }

    @Override
    public int minusGoodsNum(int goodsId, int minusNum, String expireTime) {
        byte[] threadId = String.valueOf(Thread.currentThread().getId()).getBytes(StandardCharsets.UTF_8);
        byte[] lookKey = (GOODS_LOOK_PRE + goodsId).getBytes(StandardCharsets.UTF_8);
        byte[] goodsKey = (GOODS_KEY_PRE + goodsId).getBytes(StandardCharsets.UTF_8);
        String valueOf = String.valueOf(minusNum);
        Long execute = redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.eval(ADD_GOODS_LOCK.getBytes(StandardCharsets.UTF_8)
                        , ReturnType.INTEGER
                        , 1
                        , lookKey
                        , threadId
                        , expireTime.getBytes(StandardCharsets.UTF_8)));
        if (execute != null && StringUtils.equals(LOCK_SUCCESS, Long.toString(execute))) {
            Long result = redisTemplate.execute((RedisCallback<Long>) connection ->
                    connection.eval(NEW_MINUS_GOODS_NUM.getBytes(StandardCharsets.UTF_8)
                            , ReturnType.INTEGER
                            , 2
                            , GOODS_HASH_KEY.getBytes(StandardCharsets.UTF_8)
                            , goodsKey
                            , valueOf.getBytes(StandardCharsets.UTF_8)));
            if (result != null && StringUtils.equals(Long.toString(result), LOCK_SUCCESS)) {
                redisTemplate.execute((RedisCallback<Long>) connection ->
                        connection.eval(UN_GOODS_LOCK.getBytes(StandardCharsets.UTF_8),
                                ReturnType.INTEGER
                                , 1
                                , lookKey
                                , threadId));
                return 1;
            }
        }
        return 0;
    }
}
