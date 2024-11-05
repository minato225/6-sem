package dao;

import carservice.model.Brand;
import carservice.model.Order;
import dao.exceptions.DaoException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DaoOrder extends Dao {
    private static final String SelectAllOrderTraces =
            "select * from `order`";

    private static final String SelectOrdersInfoByCarDriver =
            "select * from `order` " +
                    "where Driver_Id=" +
                    "   (select id from cardriver where Name=?)";

    private static final String OrderCarDriver =
            "update `order`" +
                    "set Driver_Id = " +
                    "   (select id from cardriver where Name = ?)" +
                    "where concat(id, Brand, Mileage) =" +
                    "   (select concat(?, Brand, Mileage) from cardriver where Name = ? and State = 1)";

    private static final String UpdateFinishState =
            "update `order` " +
                    "set isFinished = 1 " +
                    "where Driver_Id=" +
                    "   (select id from cardriver where Name=?)";

    private static final String AddOrder =
            "insert into `order`" +
                    "(brand, mileage, departure, destination, starttime) " +
                    "values(?,?,?,?,?)";

    private static final String DeleteOrder =
            "delete from `order` " +
                    "where id=?";

    public ArrayList<Order> selectAllOrderTraces() {
        compileStatement(SelectAllOrderTraces);
        executeStatement();

        return getOrdersFromResult();
    }

    public Order selectOrdersInfoByCarDriver(String carDriverName) {
        compileStatement(SelectOrdersInfoByCarDriver);

        try {
            preparedStatement.setString(1, carDriverName);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        executeStatement();
        Order order = null;
        try {
            if (!result.next()) return null;

            order = getOrderFromResult();
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't get data from ResultSet!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return order;
    }

    public String orderCarDriver(String carDriverName, int OrderId) {
        compileStatement(OrderCarDriver);
        try {
            preparedStatement.setString(1, carDriverName);
            preparedStatement.setInt(2, OrderId);
            preparedStatement.setString(3, carDriverName);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        var res = executeUpdateStatement();
        if (res == 0) return "Some Error!";

        return "Success";
    }

    public String updateFinishState(String carDriverName) {
        compileStatement(UpdateFinishState);
        try {
            preparedStatement.setString(1, carDriverName);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        var res = executeUpdateStatement();
        if (res == 0) return "Some Error!";

        return "Success";
    }

    public String addOrder(Order order) {
        compileStatement(AddOrder);
        try {
            preparedStatement.setString(1, order.brand().toString());
            preparedStatement.setInt(2, order.mileage());
            preparedStatement.setString(3, order.departure());
            preparedStatement.setString(4, order.destination());
            preparedStatement.setTimestamp(5, new Timestamp(order.startTime().getTime()));
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        var res = executeUpdateStatement();

        if (res == 0) return "Add Order error!";
        return "Success";
    }

    public String deleteOrder(int orderId) {
        compileStatement(DeleteOrder);
        try {
            preparedStatement.setInt(1, orderId);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        var res = executeUpdateStatement();

        if (res == 0) return "Delete error!";
        return "Success";
    }

    private ArrayList<Order> getOrdersFromResult() {
        var orders = new ArrayList<Order>();

        try {
            while (result.next())
                orders.add(getOrderFromResult());
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't get data from ResultSet!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    private Order getOrderFromResult() throws SQLException {
        var id = result.getInt("id");
        var brand = Brand.valueOf(result.getString("Brand"));
        var mileage = result.getInt("Mileage");
        var isFinished = result.getBoolean("isFinished");
        var departure = result.getString("Departure");
        var destination = result.getString("Departure");
        var start = result.getDate("StartTime");

        return new Order(id, brand, mileage, isFinished, departure, destination, start);
    }
}
