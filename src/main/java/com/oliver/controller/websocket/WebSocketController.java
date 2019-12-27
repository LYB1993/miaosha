package com.oliver.controller.websocket;

import com.oliver.controller.goods.GoodsController;
import com.oliver.socket.GoodsWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import javax.annotation.Resource;

/**
 * com.oliver.controller.websocket.WebSocketController
 *
 * @author oliver
 * @date 2019/12/27 19:36
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    

    @Resource(name = "goodsWebSocket")
    private GoodsWebSocket goodsWebSocket;

    @GetMapping("send")
    public void sendGoodsMessage() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("start send message");
        }
        WebSocketMessage<String> message = new TextMessage("Hello");
        goodsWebSocket.sendAll(message);
    }
}
