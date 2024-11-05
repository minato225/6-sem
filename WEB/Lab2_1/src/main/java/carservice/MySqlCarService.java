package carservice;

import dao.DaoCarDriver;
import dao.DaoOrder;
import dao.exceptions.DaoException;
import entity.*;

import java.util.stream.Collectors;

public class MySqlCarService implements ICarService {
    private final DaoOrder daoOrder = new DaoOrder();
    private final DaoCarDriver daoCarDriver = new DaoCarDriver();

    @Override
    public String selectAllOrderTraces() {
        return daoOrder.selectAllOrderTraces().stream().map(Carorder::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String selectOrdersInfoByCarDriver(String carDriverName) {
        var order = daoOrder.selectOrdersInfoByCarDriver(carDriverName);
        if (order == null)
            return "Select is null";

        return order.toString();
    }

    @Override
    public String selectBrokenCars() {
        return daoCarDriver.selectBrokenCars().stream().map(Car::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String orderCarDriver(String carDriverName, int orderId) throws DaoException {
        return daoOrder.orderCarDriver(carDriverName, orderId);
    }

    @Override
    public String deleteCarDriver(String carDriverName) throws DaoException {
        return daoCarDriver.deleteCarDriver(carDriverName);
    }
}
