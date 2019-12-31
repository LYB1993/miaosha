package com.oliver.service;

import com.oliver.entity.Goods;

/**
 * com.oliver.service.IRedisGoodsService
 *
 * @author oliver
 * @date 2019/12/30 13:49
 */
public interface IRedisGoodsService extends IRedisLoadData<Goods>{
    /**
     * 扣除商品数量
     *
     * @param userId     用户id
     * @param goodsId    商品id
     * @param minusNum   扣除数量
     * @param expireTime 设置锁超时时间
     * @return 是否扣除成功
     */
    boolean minusGoodsNum(String userId, int goodsId, int minusNum, String expireTime);
}
