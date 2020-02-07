package com.oliver.mq.producer;

import com.oliver.entity.am.AmSeckill;
import com.oliver.mq.IMqProducerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.oliver.mq.producer.AmSeckillProducer
 *
 * @author oliver
 * @date 2020/1/7 15:34
 */
@Service
public class AmSeckillProducer implements IMqProducerService<AmSeckill> {

    public static final String QUEUE_NAME = "miaosha_amseckill_queue";

    public static final String EXCHANGE_NAME = "miaosha_amseckill_exchange";

    public static final String KEY = "miaosha_amseckill";

    public static final String EXCHANGE_DLX_NAME = "miaosha_amseckill_dlx_exchange";
    public static final String DLX_KEY = "miaosha_amseckill_dlx";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RabbitListener
    @Override
    public void send(AmSeckill amSeckill) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, KEY, amSeckill);
    }
}
