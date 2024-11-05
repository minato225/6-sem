package bsu.zlatamigas.webLab67Tomcat9.util.validator;

import java.util.Map;

public interface FormValidator {

    Map<String, String> validateForm(Map <String, String[]> data);
}
