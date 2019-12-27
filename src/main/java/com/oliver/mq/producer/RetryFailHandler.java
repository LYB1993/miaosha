package com.oliver.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * com.oliver.mq.producer.RetryFaileHandler
 *
 * @author oliver
 * @date 2019/12/27 15:08
 */
@RabbitListener(bindings = @QueueBinding(value = @Queue(RetryFailHandler.QUEUE_NAME), exchange = @Exchange(
        value = RetryFailHandler.EXCHANGE_NAME, type = ExchangeTypes.TOPIC), key = RetryFailHandler.KEY
))
@Service
public class RetryFailHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryFailHandler.class);

    static final String QUEUE_NAME = "miaosha_goods_dlx_queue";

    static final String EXCHANGE_NAME = "miaosha_goods_dlx_exchange";

    static final String KEY = "miaosha_goods_dlx";

    @RabbitHandler
    public void dealFailMessage(Object obj) {
        if (obj instanceof Message) {
            byte[] body = ((Message) obj).getBody();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ObjType:{}", new String(body));
            }
        }
    }
}
