package bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.finish;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.Command;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router;
import bsu.zlatamigas.webLab67Tomcat9.exception.CommandException;
import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.Product;
import bsu.zlatamigas.webLab67Tomcat9.model.service.ProductService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.ProductServiceImpl;
import bsu.zlatamigas.webLab67Tomcat9.util.validator.FormValidator;
import bsu.zlatamigas.webLab67Tomcat9.util.validator.impl.AddProductFormValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;

public class FinishAddProductCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();


        FormValidator validator = AddProductFormValidator.getInstance();
        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, String> validationFeedback = validator.validateForm(requestParameters);

        String page = ADD_PRODUCT;

        if(!validationFeedback.isEmpty()){
            request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, validationFeedback);
            return new Router(page, FORWARD);
        }

        String name = request.getParameter(PARAMETER_PRODUCT_NAME);
        String costStr = request.getParameter(PARAMETER_PRODUCT_COST);

        ProductService productService = ProductServiceImpl.getInstance();
        double cost;
        try {
            cost = Double.parseDouble(costStr);
            Product product = new Product(name, cost);
            productService.addProduct(product);

            page = PRODUCTS;
            session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        } catch (NumberFormatException | ServiceException e){
            throw new CommandException(e);
        }

        return new Router(page, FORWARD);
    }
}
