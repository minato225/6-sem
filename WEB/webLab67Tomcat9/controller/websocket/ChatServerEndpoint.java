package bsu.zlatamigas.webLab67Tomcat9.controller.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
        value = "/controller/chatServerEndpoint",
        configurator = ChatServerConfigurator.class)
public class ChatServerEndpoint {

    private static final Logger logger = LogManager.getLogger();

    private static final String USERNAME_PROPERTY = "username";
    private static final String MESSAGE = "message";
    private static Set<Session> chatUsers = Collections.synchronizedSet(new HashSet<>());


    @OnOpen
    public void handleOpen(EndpointConfig endpointConfig, Session userSession) {
        chatUsers.add(userSession);
    }

    @OnClose
    public void handleClose(Session userSession) {
        chatUsers.remove(userSession);
    }

    @OnError
    public void handleError(Throwable throwable) {
        logger.error(throwable);
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) {
        String username = (String) userSession.getUserProperties().get(USERNAME_PROPERTY);
        if (username != null) {
            chatUsers.stream().forEach(user -> {
                try {
                    user.getBasicRemote().sendText(username + ": " + message);
                } catch (IOException e) {
                    logger.error(e);
                }
            });
        }
    }
}
