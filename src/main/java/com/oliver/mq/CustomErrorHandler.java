package com.oliver.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Service;

/**
 * com.oliver.mq.CustomErrorHandler
 *
 * @author oliver
 * @date 2019/12/27 17:00
 */
@Service
public class CustomErrorHandler implements RabbitListenerErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorHandler.class);

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        if (exception != null) {
            LOGGER.error("Exception:{}", exception.getMessage());
            return exception.getMessage();
        }
        return null;
    }
}
