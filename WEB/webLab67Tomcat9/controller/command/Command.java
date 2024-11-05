package bsu.zlatamigas.webLab67Tomcat9.controller.command;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
