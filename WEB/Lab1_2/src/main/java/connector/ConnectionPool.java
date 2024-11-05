package connector;

import connector.exceptions.MyConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements AutoCloseable {

    public static BlockingQueue<Connection> connections;
    private static ConnectionPool instance;
    public int initConnectionsCount = 10;


    public ConnectionPool(String driver, String url, String user, String password){

        if (instance != null) return;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connections = new ArrayBlockingQueue<>(initConnectionsCount);

        try {
            for (int i = 0; i < initConnectionsCount; i++) {
                var connection = DriverManager.getConnection(url, user, password);
                connections.add(connection);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    /**
     * return instance ConnectionPool or create it
     *
     * @return instance of Singleton
     */
    public static synchronized ConnectionPool getInstance(String driver, String url, String user, String password){
        if (instance == null)
            instance = new ConnectionPool(driver, url, user, password);
        return instance;
    }

    public synchronized Connection getConnection() throws MyConnectorException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new MyConnectorException("Failed to get connection from pool", e);
        }
    }

    /**
     * return the connection to pool
     *
     * @param connection to add back to pool
     */
    public synchronized void releaseConnection(Connection connection, String url, String user, String password) throws MyConnectorException {
        try {
            if (connection.isClosed()) {
                var newConnection = DriverManager.getConnection(url, user, password);
                connections.add(newConnection);
            } else {
                connections.add(connection);
            }
        } catch (SQLException e) {
            throw new MyConnectorException("Failed to establish connection", e);
        }
    }

    public static synchronized void closeConnection() throws MyConnectorException {
        try {
            while (connections.size() > 0) {
                connections.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            throw new MyConnectorException("Error! Can't close connection", e);
        }
    }

    @Override
    public void close() throws MyConnectorException {
        closeConnection();
    }
}