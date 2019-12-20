package com.oliver.service;

import java.util.List;

/**
 * com.oliver.service.IRedisService
 *
 * @author oliver
 * @date 2019/12/19 9:42
 */
public interface IRedisService<T> {
    /**
     * 加锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超时时间
     * @return 是否获取成功
     */
    boolean tryGetDistributedLock(String lockKey, String requestId, String expireTime);

    /**
     * 添加键值对
     *
     * @param key   key
     * @param value 值
     * @return 是否添加成功
     */
    boolean setValue(String key, String value);

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求id
     * @return 是否释放成功
     */
    boolean unLockDistributedLock(String lockKey, String requestId);


}
