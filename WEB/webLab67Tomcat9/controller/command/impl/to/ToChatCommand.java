package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.to;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_CURRENT_PAGE;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.CHAT;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class ToChatCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String page = CHAT;
        HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        return new Router(page, FORWARD);
    }
}
