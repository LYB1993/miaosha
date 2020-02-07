package com.oliver.mq.producer;

import com.oliver.entity.Goods;
import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.IMqProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.oliver.mq.producer.RabbitmqServiceImpl
 *
 * @author oliver
 * @date 2019/12/26 12:48
 */
@Service
public class MiaoShaGoodsProducer implements IMqProducerService<ResultVO> {

    public static final String QUEUE_NAME = "miaosha_goods_queue";

    public static final String EXCHANGE_NAME = "miaosha_goods_exchange";

    public static final String KEY = "miaosha_goods";

    public static final String EXCHANGE_DLX_NAME = "miaosha_goods_dlx_exchange";
    public static final String DLX_KEY = "miaosha_goods_dlx";



    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(ResultVO goods) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME
                , KEY
                , ResultVO.success("send Message Test"));
    }
}
