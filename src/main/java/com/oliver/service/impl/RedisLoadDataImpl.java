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

    private static final String ADD_GOODS_NUM = "return redis.call('setNX',KEYS[1],ARGV[1])==1";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void loadDataToRedis(List<Goods> data) {
    }

    @Override
    public Long loadGoodsNum(int goodsId, int goodsNum) {
        return redisTemplate.opsForSet().add(goodsId + "", goodsNum + "");
    }

    @Override
    public int getGoodsNum(int goodsId) {
        String goodsNum = redisTemplate.opsForValue().get(goodsId);
        if (StringUtils.isNotBlank(goodsNum)) {
            return Integer.parseInt(goodsNum);
        }
        return 0;
    }
}
