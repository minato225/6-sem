package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.to;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.service.OrderService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.DELAYED_ORDERS;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.HOME;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class RefreshDelayedCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        OrderService service = OrderServiceImpl.getInstance();

        try {
            service.refreshDelayed(Integer.parseInt(request.getParameter("order_id")));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(HOME, FORWARD);
    }
}
