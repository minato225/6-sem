package bsu.zlatamigas.webLab67Tomcat9.util.validator;

public final class ValidatorPatternHolder {

    public static final String EMAIL_PATTERN = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    public static final String PASSWORD_PATTERN = "^[\\p{Alnum}\\p{Punct}]{6,20}$";

    public static final String PRODUCT_NAME_PATTERN = "^[\\p{Alnum}\\p{Punct} ]{2,20}$";
    public static final String PRODUCT_COST_PATTERN = "^\\d{1,10}([\\.,]\\d{1,2})?$";


    private ValidatorPatternHolder(){}
}
