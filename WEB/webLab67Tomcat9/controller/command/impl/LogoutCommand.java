package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_CURRENT_PAGE;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_LOCALISATION;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.DEFAULT;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        String page = DEFAULT;
        String localisation = (String) session.getAttribute(ATTRIBUTE_LOCALISATION);

        session.invalidate();
        session = request.getSession(true);

        session.setAttribute(ATTRIBUTE_LOCALISATION, localisation);
        session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        return new Router(page, FORWARD);
    }
}
