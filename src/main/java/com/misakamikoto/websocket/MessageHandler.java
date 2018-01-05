package com.misakamikoto.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MisakaMikoto on 2018. 1. 5..
 */
public class MessageHandler {

    private static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private List<Session> sessionList = null;

    MessageHandler() {
        this.sessionList = new ArrayList<Session>();
    }

    protected void destroy() {
        this.sessionList = null;
    }

    protected void addSession(Session session) {
        this.sessionList.add(session);
    }

    protected void sendMessage(String message) {
        for(Session session : sessionList) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
