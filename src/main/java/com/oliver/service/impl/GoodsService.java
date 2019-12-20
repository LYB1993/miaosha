package com.oliver.service.impl;

import com.oliver.entity.Goods;
import com.oliver.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.oliver.service.impl.GoodsService
 *
 * @author oliver
 * @date 2019/12/19
 */
@Service
public class GoodsService implements IGoodsService {
    @Override
    public void loadGoodsToRedis(List<Goods> goods) {

    }

    @Override
    public Goods loadGoods(int goodsId) {
        return null;
    }
}
