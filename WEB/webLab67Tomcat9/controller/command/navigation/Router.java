package bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.*;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageNavigation.DEFAULT;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.FORWARD;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.Router.PageChangeType.REDIRECT;

public class Router {

    private String page = DEFAULT;
    private PageChangeType type = FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT;
    }

    public Router() {
    }

    public Router(String page) {
        this.page = (page != null ? page : DEFAULT);
    }

    public Router(String page, PageChangeType type) {
        this.page = (page != null ? page : DEFAULT);
        this.type = (type != null ? type : FORWARD);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : DEFAULT);
    }

    public void setRedirect() {
        this.type = REDIRECT;
    }

    public void setForward() {
        this.type = FORWARD;
    }

    public PageChangeType getType() {
        return type;
    }
}
