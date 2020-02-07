package com.oliver.mq;

/**
 * com.oliver.mq.IMqCustomerService
 *
 * @author oliver
 * @date 2020/1/7 15:43
 */
public interface IMqCustomerService<T> {
    void receive(T t);
}
