package bsu.zlatamigas.webLab67Tomcat9.controller;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.CommandType;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.PARAMETER_COMMAND;

@WebServlet(name = "AppController", urlPatterns = { "/controller", "/pages/controller"})
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void init() {
        logger.info("---------------> Servlet init");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);

        String commandStr = request.getParameter(PARAMETER_COMMAND);
        Command command = CommandType.defineCommand(commandStr);

        try {

            Router router = command.execute(request);
            String page = router.getPage();

            switch (router.getType()) {
                case FORWARD:
                    request.getRequestDispatcher(page).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + page);
                    break;
                default:
                    logger.error("Invalid routing type!");
                    response.sendError(500);

            }

        } catch (CommandException e) {
            logger.error("Error while command execution: " + commandStr, e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        logger.info("---------------> Servlet destroy");
    }
}
