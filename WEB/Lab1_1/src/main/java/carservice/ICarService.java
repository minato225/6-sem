package carservice;

import carservice.model.Car;
import carservice.model.CarDriver;
import carservice.model.Order;

public interface ICarService {

    String selectAllOrderTraces();

    String selectOrdersInfoByCarDriver(String carDriverName);

    String selectBrokenCars();

    String orderCarDriver(String carDriverName, int OrderId);

    String putCarForRepair(int carNumber);

    String addCarDriver(CarDriver carDriver, Car car);

    String addOrder(Order order);

    String deleteCarDriver(String carDriverName);

    String deleteOrder(int OrderId);

    String finishOrder(String carDriverName);
}
