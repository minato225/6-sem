package connector;

import connector.exceptions.MyConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {

    private Connection connection;

    public JdbcConnector(String driver, String url, String user, String password) {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwable) {
            try {
                throw new MyConnectorException("Can't make connection", throwable);
            } catch (MyConnectorException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection == null) return;

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}