package bsu.zlatamigas.webLab67Tomcat9.controller.websocket;

import bsu.zlatamigas.webLab67Tomcat9.model.entity.User;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_USER;

public class ChatServerConfigurator extends ServerEndpointConfig.Configurator {

    private static final String USERNAME_PROPERTY = "username";

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        HttpSession session = (HttpSession) request.getHttpSession();
        User user = (User)session.getAttribute(ATTRIBUTE_USER);

        if(user.getStatus() == UserStatus.ADMIN){
            sec.getUserProperties().put(USERNAME_PROPERTY, "Admin");
        } else {
            sec.getUserProperties().put(USERNAME_PROPERTY, user.getEmail());
        }
    }
}
