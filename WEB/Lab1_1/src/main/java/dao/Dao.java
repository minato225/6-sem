package dao;

import connector.JdbcConnector;
import dao.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class Dao implements AutoCloseable {
    protected JdbcConnector connector;
    protected PreparedStatement preparedStatement;
    protected ResultSet result;

    public Dao() {
        var resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        this.connector = new JdbcConnector(driver, url, user, pass);
    }

    protected void compileStatement(String query) {
        try {
            preparedStatement = connector.getConnection().prepareStatement(query);
        } catch (SQLException throwable) {
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
        }

        return res;
    }

    @Override
    public void close() {
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

        connector.closeConnection();
    }
}
