package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.start;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.AUTHORISATION;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class StartAuthenticationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(AUTHORISATION, FORWARD);
    }
}
