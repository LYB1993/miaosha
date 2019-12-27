package com.oliver.mq.producer;

import com.alibaba.fastjson.JSONObject;
import com.oliver.entity.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


/**
 * com.oliver.mq.producer.MiaoShaGoodsService
 *
 * @author oliver
 * @date 2019/12/26 13:33
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = MiaoShaGoodsService.QUEUE_NAME,
                arguments = {@Argument(name = "x-dead-letter-exchange", value = "miaosha_goods_dlx_exchange")
                        , @Argument(name = "x-dead-letter-routing-key", value = "miaosha_goods_dlx")}),
        exchange = @Exchange(value = MiaoShaGoodsService.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = MiaoShaGoodsService.KEY
), errorHandler = "")
public class MiaoShaGoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiaoShaGoodsService.class);

    static final String QUEUE_NAME = "miaosha_goods_queue";

    static final String EXCHANGE_NAME = "miaosha_goods_exchange";

    static final String KEY = "miaosha_goods";


    @RabbitHandler
    public void saveDataToDb(ResultVO resultVO) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("resultVO:{}", resultVO);
        }
    }
}
