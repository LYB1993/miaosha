package com.oliver.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * com.oliver.socket.GoodsWebSocket
 *
 * @author oliver
 * @date 2019/12/25
 */
@Service
public class GoodsWebSocket implements WebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsWebSocket.class);

    private static Set<WebSocketSession> allSession =
            new ConcurrentSkipListSet<>(Comparator.comparingInt(Object::hashCode));

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("session-Connection:{}", session);
        }
        allSession.add(session);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("session-message:{},message:{}", session, message);
        }
        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        allSession.remove(session);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("session-close:{}", session);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void sendAll(WebSocketMessage<?> message) {
        allSession.forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(message);
            } catch (IOException e) {
                LOGGER.error("sendMessage is error:{}", e.getMessage());
            }
        });
    }
}
