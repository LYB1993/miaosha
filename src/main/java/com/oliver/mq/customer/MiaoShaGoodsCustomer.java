package com.oliver.mq.customer;

import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.IMqCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.oliver.mq.producer.MiaoShaGoodsProducer.*;

/**
 * com.oliver.mq.customer.MiaoShaGoodsService
 *
 * @author oliver
 * @date 2019/12/26 13:33
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = QUEUE_NAME,
                arguments = {@Argument(name = "x-dead-letter-exchange", value = EXCHANGE_DLX_NAME)
                        , @Argument(name = "x-dead-letter-routing-key", value = DLX_KEY)}),
        exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = KEY
), errorHandler = "")
public class MiaoShaGoodsCustomer implements IMqCustomerService<ResultVO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiaoShaGoodsCustomer.class);

    @RabbitHandler
    @Override
    public void receive(ResultVO resultVO) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("resultVO:{}", resultVO);
        }
    }
}
