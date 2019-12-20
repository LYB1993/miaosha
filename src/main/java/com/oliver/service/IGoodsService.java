package com.oliver.service;

import com.oliver.entity.Goods;

import java.util.List;

/**
 * com.oliver.service.IGoodsService
 *
 * @author oliver
 * @date 2019/12/18 15:33
 */
public interface IGoodsService {

    void loadGoodsToRedis(List<Goods> goods);

    Goods loadGoods(int goodsId);
}
