package com.oliver.mq.customer;

import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.IMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.oliver.mq.customer.RabbitmqServiceImpl
 *
 * @author oliver
 * @date 2019/12/26 12:48
 */
@Service
public class RabbitmqServiceImpl implements IMqService {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send() {
        rabbitTemplate.convertAndSend("miaosha_goods_exchange"
                , "miaosha_goods"
                , ResultVO.success("send Message Test"));
    }
}
