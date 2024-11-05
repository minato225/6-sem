package carservice;

import carservice.model.Car;
import carservice.model.CarDriver;
import carservice.model.Order;
import dao.DaoCarDriver;
import dao.DaoOrder;

import java.util.stream.Collectors;

public class MySqlCarService implements ICarService {
    private final DaoOrder daoOrder = new DaoOrder();
    private final DaoCarDriver daoCarDriver = new DaoCarDriver();


    @Override
    public String selectAllOrderTraces() {
        return daoOrder.selectAllOrderTraces().stream().map(Order::toString).collect(Collectors.joining("\n"));
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
    public String orderCarDriver(String carDriverName, int orderId) {
        return daoOrder.orderCarDriver(carDriverName, orderId);
    }

    @Override
    public String putCarForRepair(int carNumber) {
        return daoCarDriver.putCarForRepair(carNumber);
    }

    @Override
    public String addCarDriver(CarDriver carDriver, Car car) {
        return daoCarDriver.addCarDriver(carDriver, car);
    }

    @Override
    public String addOrder(Order order) {
        return daoOrder.addOrder(order);
    }

    @Override
    public String deleteCarDriver(String carDriverName) {
        return daoCarDriver.deleteCarDriver(carDriverName);
    }

    @Override
    public String deleteOrder(int orderId) {
        return daoOrder.deleteOrder(orderId);
    }

    @Override
    public String finishOrder(String carDriverName) {
        return daoOrder.updateFinishState(carDriverName);
    }
}
