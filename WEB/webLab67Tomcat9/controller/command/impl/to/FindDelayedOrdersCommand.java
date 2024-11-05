package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.to;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.DeliveryStatus;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.Order;
import bsu.zlatamigas.webLab67Tomcat9.model.service.OrderService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_CURRENT_PAGE;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_DELAYED_ORDERS;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class FindDelayedOrdersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService service = OrderServiceImpl.getInstance();

        String page;
        HttpSession session = request.getSession();
        try {

            List<Order> orders = service.findStatusOrders(DeliveryStatus.DELAYED);

            session.setAttribute(ATTRIBUTE_DELAYED_ORDERS, orders);
            page = PageNavigation.DELAYED_ORDERS;

            session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(page, FORWARD);
    }
}
