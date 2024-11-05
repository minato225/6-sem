package bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation;

public final class PageDataHolder {

    // Request parameters

    // Shared
    public static final String PARAMETER_LOCALISATION = "localisation";
    public static final String PARAMETER_COMMAND = "command";
    // Log in/up
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    // Find orders
    public static final String PARAMETER_BEFORE_DATE = "before_date";
    // Add product
    public static final String PARAMETER_PRODUCT_NAME = "product_name";
    public static final String PARAMETER_PRODUCT_COST = "product_cost";

    // Session attributes

    // Shared
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_LOCALISATION = "localisation";
    public static final String ATTRIBUTE_CURRENT_PAGE = "current_page";
    public static final String ATTRIBUTE_PREVIOUS_PAGE = "previous_page";
    // Orders and products lists
    public static final String ATTRIBUTE_DELAYED_ORDERS = "delayed_orders";
    public static final String ATTRIBUTE_DELIVERY_BEFORE_DATE_ORDERS = "delivery_before_date_orders";
    public static final String ATTRIBUTE_CLIENT_ORDERS = "client_orders";
    public static final String ATTRIBUTE_PRODUCTS = "products";


    // Request
    public static final String REQUEST_ATTRIBUTE_USER_INVALID = "user_invalid";
    public static final String REQUEST_ATTRIBUTE_FORM_INVALID = "form_invalid";


    private PageDataHolder() {};
}
