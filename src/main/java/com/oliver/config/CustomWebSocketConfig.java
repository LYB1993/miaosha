package com.oliver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * com.oliver.config.CustomWebSocketConfig
 *
 * @author oliver
 * @date 2019/12/25
 */
@Configuration
public class CustomWebSocketConfig implements WebSocketConfigurer {

    @Resource(name = "goodsWebSocket")
    private WebSocketHandler goodsWebSocket;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(goodsWebSocket, "/goods")
                .setAllowedOrigins("*");
    }
}
