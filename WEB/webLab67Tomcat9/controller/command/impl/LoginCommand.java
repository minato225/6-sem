package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.User;
import bsu.zlatamigas.webLab67Tomcat9.model.service.UserService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.UserServiceImpl;
import bsu.zlatamigas.webLab67Tomcat9.util.locale.LocalisedMessageKey;
import bsu.zlatamigas.webLab67Tomcat9.util.validator.FormValidator;
import bsu.zlatamigas.webLab67Tomcat9.util.validator.impl.LogInFormValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.AUTHORISATION;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.HOME;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.REDIRECT;

public class LoginCommand implements Command {

    public static final String LOCALISED_LOGIN_ERROR_MSG = "login_msg";
    public static final String LOGIN_MSG = "Incorrect login or password";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();

        String login = request.getParameter(PARAMETER_EMAIL);
        String password = request.getParameter(PARAMETER_PASSWORD);

        FormValidator validator = LogInFormValidator.getInstance();
        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, String> validationFeedback = validator.validateForm(requestParameters);

        String page = AUTHORISATION;
        PageChangeType pageChangeType = FORWARD;

        if(!validationFeedback.isEmpty()){
            request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, validationFeedback);
            return new Router(page, pageChangeType);
        }

        UserService service = UserServiceImpl.getInstance();
        try {

            Optional<User> userFromDb = service.authenticate(login, password);

            if (userFromDb.isPresent()) {

                User user = userFromDb.get();
                session.setAttribute(ATTRIBUTE_USER, user);
                page = HOME;
                pageChangeType = REDIRECT;
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_USER_INVALID, LocalisedMessageKey.MESSAGE_USER_INVALID);
            }

            session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw  new CommandException(e.getMessage(), e);
        }

        return new Router(page, pageChangeType);
    }
}
