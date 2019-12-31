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

    int setValue(String key, String value);

}
