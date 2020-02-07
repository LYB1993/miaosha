package com.oliver.mq.customer;

import com.oliver.entity.am.AmSeckill;
import com.oliver.entity.am.AmSeckillDAO;
import com.oliver.mq.IMqCustomerService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.oliver.mq.producer.AmSeckillProducer.*;

/**
 * com.oliver.mq.customer.AmseckillCustomer
 *
 * @author oliver
 * @date 2020/1/7 15:46
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = QUEUE_NAME,
                arguments = {@Argument(name = "x-dead-letter-exchange", value = EXCHANGE_DLX_NAME)
                        , @Argument(name = "x-dead-letter-routing-key", value = DLX_KEY)}),
        exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = KEY
), errorHandler = "")
public class AmseckillCustomer implements IMqCustomerService<AmSeckill> {

    private static final String AM_KEY = "amseckill_data_hash";
    private static final String PREFIX_KEY = "amseckill_";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private AmSeckillDAO baseDao;


    @RabbitHandler
    @Override
    public void receive(AmSeckill amSeckill) {
        if (amSeckill.getCreateDatetime() == null) {
            amSeckill = baseDao.selectByPrimaryKey(amSeckill.getId());
        }
        Date startDate = amSeckill.getStartDatetime();
        Date endDate = amSeckill.getEndDatetime();
        long timeout = endDate.getTime() - new Date().getTime();
        redisTemplate.opsForValue().set(PREFIX_KEY + amSeckill.getId(), String.valueOf(startDate.getTime()), timeout, TimeUnit.MILLISECONDS);
    }
}
