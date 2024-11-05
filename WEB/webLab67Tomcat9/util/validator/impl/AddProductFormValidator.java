package bsu.zlatamigas.webLab67Tomcat9.util.validator.impl;

import bsu.zlatamigas.webLab67Tomcat9.util.validator.FormValidator;

import java.util.HashMap;
import java.util.Map;

import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.PARAMETER_PRODUCT_COST;
import static bsu.zlatamigas.webLab67Tomcat9.controller.command.navigation.PageDataHolder.PARAMETER_PRODUCT_NAME;
import static bsu.zlatamigas.webLab67Tomcat9.util.locale.LocalisedMessageKey.MESSAGE_PRODUCT_COST_WRONG;
import static bsu.zlatamigas.webLab67Tomcat9.util.locale.LocalisedMessageKey.MESSAGE_PRODUCT_NAME_WRONG;
import static bsu.zlatamigas.webLab67Tomcat9.util.validator.ValidatorPatternHolder.PRODUCT_COST_PATTERN;
import static bsu.zlatamigas.webLab67Tomcat9.util.validator.ValidatorPatternHolder.PRODUCT_NAME_PATTERN;

public class AddProductFormValidator implements FormValidator {

    private static FormValidator instance;

    private AddProductFormValidator() {
    }

    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new AddProductFormValidator();
        }
        return instance;
    }

    @Override
    public Map<String, String> validateForm(Map<String, String[]> data) {

        Map<String, String> validationResult = new HashMap<>();

        if (!data.get(PARAMETER_PRODUCT_NAME)[0].matches(PRODUCT_NAME_PATTERN)) {
            validationResult.put(PARAMETER_PRODUCT_NAME, MESSAGE_PRODUCT_NAME_WRONG);
        }
        if (!data.get(PARAMETER_PRODUCT_COST)[0].matches(PRODUCT_COST_PATTERN)) {
            validationResult.put(PARAMETER_PRODUCT_COST, MESSAGE_PRODUCT_COST_WRONG);
        }

        return validationResult;
    }
}
