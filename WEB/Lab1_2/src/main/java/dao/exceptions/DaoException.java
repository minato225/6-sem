package dao.exceptions;

import java.sql.SQLException;

/**
 * DAO exception
 *
 * @author Roman
 */
public class DaoException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     * @param throwable throwable
     */
    public DaoException(String message, Throwable throwable) {
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