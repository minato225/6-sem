package connector.exceptions;

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
    public MyConnectorException(String message, Throwable throwable) {
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
