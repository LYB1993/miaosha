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

    /**
     * 加载商品数据到数据库
     *
     * @param goods 商品数据
     */
    void loadGoodsToRedis(List<Goods> goods);

    /**
     * 根据商品id拿到数据
     *
     * @param goodsId 商品id
     * @return goods
     */
    Goods loadGoods(int goodsId);
}
