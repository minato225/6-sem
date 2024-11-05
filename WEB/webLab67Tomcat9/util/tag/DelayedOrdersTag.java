package bsu.zlatamigas.webLab67Tomcat9.util.tag;

import bsu.zlatamigas.webLab67Tomcat9.model.entity.Order;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.User;
import bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.ATTRIBUTE_USER;

public class DelayedOrdersTag extends BodyTagSupport {


    private List<Order> items;
    private int itemPos;
    private boolean isAdmin;

    public void setItems(List<Order> items) {
        this.items = items;
        itemPos = 0;
    }

    @Override
    public int doStartTag() throws JspException {

        try{
            User user = (User)pageContext.getSession().getAttribute(ATTRIBUTE_USER);
            isAdmin = (user!= null && user.getStatus()== UserStatus.ADMIN);

            pageContext.getOut().write("<table class=\"table\">\n" +
                    "        <thead class=\"thead-dark\">\n" +
                    "        <tr>\n" +
                    "            <th scope=\"col\">ID</th>\n" +
                    "            <th scope=\"col\">Delivery date</th>\n" +
                    "            <th scope=\"col\">Client ID</th>\n" +
                            (isAdmin ?  "            <th scope=\"col\">Refresh</th>\n" : "") +
                    "        </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>" +
                    "            <tr>\n" +
                    "                <td scope=\"row\">");
        } catch (IOException e) {
            throw  new JspTagException(e.getMessage());
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {

        if(itemPos < items.size()){
            Order order = items.get(itemPos);
            itemPos++;
            try{
                pageContext.getOut().write(order.getId() + "</td>\n" +
                        "                <td>"+order.getDeliveryDate()+"</td>\n" +
                        "                <td>"+ order.getClientId()+"</td>\n" +
                        (isAdmin ?  "            <td>"+
                                "<form action=\"controller\" method=\"GET\">\n" +
                                "            <input type=\"hidden\" name=\"command\" value=\"refresh_delayed\">\n" +
                                "            <input type=\"hidden\" name=\"order_id\" value=\"" + order.getId() + "\">\n" +
                                "            <button type=\"submit\" class=\"btn btn-primary\">()</button>\n" +
                                "        </form>"
                                +"</td>\n" : "") +
                        "            </tr>"+
                        "            <tr>\n" +
                        "                <td scope=\"row\">");
            } catch (IOException e){
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }


    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</td></tr></tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
