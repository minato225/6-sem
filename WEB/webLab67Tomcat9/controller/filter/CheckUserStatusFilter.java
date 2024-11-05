package bsu.zlatamigas.webLab67Tomcat9.controller.filter;


import bsu.zlatamigas.webLab67Tomcat9.controller.command.CommandType;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.User;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.CommandType.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_USER;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.PARAMETER_COMMAND;

@WebFilter(filterName = "CheckUserStatusFilter", urlPatterns = {"/controller", "/pages/controller"})
public class CheckUserStatusFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private EnumSet<CommandType> adminCommands;
    private EnumSet<CommandType> clientCommands;
    private EnumSet<CommandType> guestCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminCommands = EnumSet.of(
                DEFAULT,
                CHANGE_LOCALISATION,
                LOGOUT,
                FIND_DELAYED_ORDERS,
                FIND_DELIVERY_BEFORE_DATE_ORDERS,
                FIND_PRODUCTS,
                START_ADD_PRODUCT,
                FINISH_ADD_PRODUCT,
                TO_CHAT,
                REFRESH_DELAYED
        );
        clientCommands = EnumSet.of(
                DEFAULT,
                CHANGE_LOCALISATION,
                LOGOUT,
                FIND_CLIENT_ORDERS,
                FIND_PRODUCTS,
                TO_CHAT
        );
        guestCommands = EnumSet.of(
                DEFAULT,
                CHANGE_LOCALISATION,
                START_AUTHENTICATION,
                LOGIN,
                FIND_PRODUCTS
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = (HttpSession) request.getSession();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String commandStr = request.getParameter(PARAMETER_COMMAND);

        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        UserStatus userStatus = user != null ? user.getStatus() : UserStatus.GUEST;

        EnumSet<CommandType> allowedCommands = null;
        switch (userStatus) {
            case ADMIN:
                allowedCommands = adminCommands;
                break;
            case CLIENT:
                allowedCommands = clientCommands;
                break;
            case GUEST:
                allowedCommands = guestCommands;
                break;
            default:
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        if (!allowedCommands.contains(CommandType.defineCommandType(commandStr))) {
            response.sendRedirect(request.getContextPath() + PageNavigation.DEFAULT);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
