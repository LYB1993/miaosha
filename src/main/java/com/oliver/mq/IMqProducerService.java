package com.oliver.mq;

/**
 * com.oliver.mq.IMqService
 *
 * @author oliver
 * @date 2019/12/26 12:47
 */
public interface IMqProducerService<T> {

    void send(T t);

}
