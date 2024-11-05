package bsu.zlatamigas.webLab67Tomcat9.controller.command;

import bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.*;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.finish.FinishAddProductCommand;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.start.StartAddProductCommand;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.start.StartAuthenticationCommand;
import bsu.zlatamigas.webLab67Tomcat9.controller.command.impl.to.*;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    CHANGE_LOCALISATION(new ChangeLocalisationCommand()),
    START_AUTHENTICATION(new StartAuthenticationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    // Find orders
    FIND_CLIENT_ORDERS(new FindClientOrdersCommand()),
    FIND_DELAYED_ORDERS(new FindDelayedOrdersCommand()),
    FIND_DELIVERY_BEFORE_DATE_ORDERS(new FindDeliveryBeforeDateOrdersCommand()),
    // CRUD products
    FIND_PRODUCTS(new FindProductsCommand()),
    START_ADD_PRODUCT(new StartAddProductCommand()),
    FINISH_ADD_PRODUCT(new FinishAddProductCommand()),
    // Chat
    TO_CHAT(new ToChatCommand()),
    REFRESH_DELAYED(new RefreshDelayedCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {

        if(commandStr == null){
            return DEFAULT.command;
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType.command;
    }

    public static CommandType defineCommandType(String commandStr) {

        if(commandStr == null){
            return DEFAULT;
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }
}
