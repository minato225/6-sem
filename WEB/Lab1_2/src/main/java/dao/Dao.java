package dao;

import connector.ConnectionPool;
import connector.exceptions.MyConnectorException;
import dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class Dao implements AutoCloseable {
    private static final String driver = ResourceBundle.getBundle("db").getString("driver");
    private static final String url = ResourceBundle.getBundle("db").getString("url");
    private static final String user = ResourceBundle.getBundle("db").getString("user");
    private static final String pass = ResourceBundle.getBundle("db").getString("password");

    protected PreparedStatement preparedStatement;
    protected ResultSet result;
    protected Connection connection;
    protected ConnectionPool dbConnectorPool;

    protected Dao() {
        dbConnectorPool = ConnectionPool.getInstance(driver, url, user, pass);
    }

    protected void compileStatement(String query) {
        try {
            connection = dbConnectorPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException | MyConnectorException throwable) {
            try {
                throw new DaoException("Error! Can't compile PreparedStatement!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }

    protected void executeStatement() {
        try {
            result = preparedStatement.executeQuery();
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't execute PreparedStatement!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    dbConnectorPool.releaseConnection(connection, url, user, pass);
                } catch (MyConnectorException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected int executeUpdateStatement() {
        int res = 0;
        try {
            res = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't execute update-PreparedStatement!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    dbConnectorPool.releaseConnection(connection, url, user, pass);
                } catch (MyConnectorException e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    @Override
    public void close() throws MyConnectorException {
        try {
            result.close();
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't close ResultSet!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        try {
            preparedStatement.close();
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't close PreparedStatement!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        ConnectionPool.closeConnection();
    }
}
