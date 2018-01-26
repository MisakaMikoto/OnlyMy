package com.misakamikoto.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by MisakaMikoto on 2018. 1. 4..
 */
/**
 * The type Server web socket.
 */
@Component
@ServerEndpoint("/serverWebSocket")
public class ServerWebSocket {
    private static Logger logger = LoggerFactory.getLogger(ServerWebSocket.class);

    private static final MessageHandler messageHandlerService = new MessageHandler();

    /**
     * Open.
     *
     * @param session the session
     */
    @OnOpen
    public void open(Session session){
        messageHandlerService.addSession(session);
        logger.debug("opening server websocket => " + session.getId());
    }

    /**
     * Message.
     *
     * @param message the message
     */
    @OnMessage
    public void message(String message){
        messageHandlerService.sendMessage(message);
    }

    /**
     * Close.
     */
    @OnClose
    public void close(){
//        messageHandler.destroy();
        logger.debug("closing server websocket");
    }

    /**
     * Error.
     *
     * @param t the t
     */
    @OnError
    public void error(Throwable t){
        logger.debug(t.getMessage());
    }
}
