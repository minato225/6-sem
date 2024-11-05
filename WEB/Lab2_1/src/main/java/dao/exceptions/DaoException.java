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
    public DaoException(String message, SQLException throwable) {
        super(message);
    }

    public DaoException(String failed_to_set_car_status, Exception e) {
        super(e);
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