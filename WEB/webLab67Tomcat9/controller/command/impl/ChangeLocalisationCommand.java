package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class ChangeLocalisationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        String localisation = request.getParameter(PARAMETER_LOCALISATION);
        session.setAttribute(ATTRIBUTE_LOCALISATION, localisation);
        String page = (String) session.getAttribute(ATTRIBUTE_CURRENT_PAGE);

        return new Router(page, FORWARD);
    }
}
