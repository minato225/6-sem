package bsu.zlatamigas.webLab67Tomcat9.controller.filter;


import bsu.zlatamigas.webLab67Tomcat9.exception.ServiceException;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.Product;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.User;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus;
import bsu.zlatamigas.webLab67Tomcat9.model.service.ProductService;
import bsu.zlatamigas.webLab67Tomcat9.model.service.impl.ProductServiceImpl;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_PRODUCTS;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_USER;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.PRODUCTS;

@WebFilter(filterName = "BeforeProductsPageFilter", dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = "/pages/products.jsp")
public class BeforeProductsPageFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);

        try {
            ProductService productService = ProductServiceImpl.getInstance();
            List<Product> products =  productService.findAllProducts();
            session.setAttribute(ATTRIBUTE_PRODUCTS, products);
        } catch (ServiceException e) {
           logger.error(e);
        }

        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        if( user!= null && user.getStatus() == UserStatus.ADMIN){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath() + PRODUCTS);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
