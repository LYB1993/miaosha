package com.oliver.controller.goods;

import com.oliver.entity.Goods;
import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.IMqService;
import com.oliver.service.IRedisGoodsService;
import com.oliver.service.IRedisLoadData;
import com.oliver.socket.GoodsWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import javax.annotation.Resource;

/**
 * com.oliver.controller.goods.GoodsController
 *
 * @author oliver
 * @date 2019/12/18
 */
@RestController()
@RequestMapping("goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private IRedisGoodsService redisLoadData;

    @GetMapping("list/{id}")
    public Object goodsList(@PathVariable("id") String goodsId) {
        return null;
    }

    @PostMapping("redis/{goodsid}/{goodsnum}")
    public Object loadGoodsNumToRedis(@PathVariable("goodsid") String goodsId, @PathVariable("goodsnum") String goodsNum) {
        return redisLoadData.setValue(goodsId, goodsNum);
    }

    @PostMapping("buy/{userid}/{goodsid}")
    public Object buyGoods(@PathVariable("userid") String userId, @PathVariable("goodsid") String goodsId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("userId:{},goodsId:{}", userId, goodsId);
        }
        if (redisLoadData.minusGoodsNum(userId, Integer.parseInt(goodsId), 1, "5")) {
            return ResultVO.success("订购成功");
        }
        return ResultVO.error("订购失败");
    }

}
