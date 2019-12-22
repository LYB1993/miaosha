package com.oliver.controller.goods;

import com.oliver.entity.Goods;
import com.oliver.service.IRedisLoadData;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private IRedisLoadData<Goods> redisLoadData;

    @GetMapping("list/{id}")
    public Object goodsList(@PathVariable("id") String goodsId) {
        return null;
    }

    @PostMapping("redis/{goodsid}/{goodsnum}")
    public Object loadGoodsNumToRedis(@PathVariable("goodsid") String goodsId, @PathVariable("goodsnum") String goodsNum) {
        return redisLoadData.loadGoodsNum(Integer.parseInt(goodsId), Integer.parseInt(goodsNum));
    }

    @PostMapping("buy/{goodsid}")
    public Object buyGoods(@PathVariable("goodsid") String goodsId) {
        return redisLoadData.minusGoodsNum(Integer.parseInt(goodsId), 1, "5");
    }

}
