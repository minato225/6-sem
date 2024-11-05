package connector.exceptions;

import java.sql.SQLException;

/**
 * database connector exception
 *
 * @author Roman
 */
public class MyConnectorException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     * @param throwable throwable
     */
    public MyConnectorException(String message, SQLException throwable) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
