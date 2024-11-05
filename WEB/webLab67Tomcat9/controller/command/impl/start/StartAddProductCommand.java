package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.start;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_CURRENT_PAGE;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.ADD_PRODUCT;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class StartAddProductCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        String page = ADD_PRODUCT;
        session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        return new Router(page, FORWARD);
    }
}
