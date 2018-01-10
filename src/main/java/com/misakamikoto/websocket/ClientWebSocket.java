package com.misakamikoto.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by MisakaMikoto on 2018. 1. 4..
 */
/**
 * The type Client web socket.
 */
@ClientEndpoint
public class ClientWebSocket {

    private static Logger logger = LoggerFactory.getLogger(ClientWebSocket.class);
    private Session session;

    /**
     * Instantiates a new Client web socket.
     *
     * @param url the url
     */
    public ClientWebSocket(String url) {
        try {
            URI endpointURI = new URI(url);
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open.
     *
     * @param session the session
     */
    @OnOpen
    public void open(Session session){
        this.session = session;
        logger.debug("opening client websocket => " + session.getId());
    }

    /**
     * Close.
     */
    @OnClose
    public void close(){
        logger.debug("closing client websocket");
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

    /**
     * Send message.
     *
     * @param message the message
     */
    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }
}
