package com.oliver.service;

import java.util.List;

/**
 * com.oliver.service.IRedisLoadData
 *
 * @author oliver
 * @date 2019/12/19 11:46
 */
public interface IRedisLoadData<T> {

    void loadDataToRedis(List<T> data);

    int loadGoodsNum(int goodsId, int goodsNum);

    int getGoodsNum(int goodsId);

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
