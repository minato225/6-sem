package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.to;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.Order;
import bsu.zlatamigas.webLab67Tomcat9.model.service.OrderService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class FindDeliveryBeforeDateOrdersCommand implements Command {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService service = OrderServiceImpl.getInstance();

        String page;
        HttpSession session = request.getSession();
        try {

            String beforeDateStr = request.getParameter(PARAMETER_BEFORE_DATE);
            Date beforeDate = new Date(new SimpleDateFormat(DATE_FORMAT).parse(beforeDateStr).getTime());

            List<Order> orders = service.findRequireDeliveryBeforeDateOrders(beforeDate);

            session.setAttribute(ATTRIBUTE_DELIVERY_BEFORE_DATE_ORDERS, orders);
            page = PageNavigation.DELIVERY_BEFORE_DATE_ORDERS;

            session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        } catch (ServiceException | ParseException e) {
            throw new CommandException(e);
        }

        return new Router(page, FORWARD);
    }
}
