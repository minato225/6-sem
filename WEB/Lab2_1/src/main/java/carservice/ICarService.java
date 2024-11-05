package carservice;

import dao.exceptions.DaoException;
import entity.*;

public interface ICarService {

    String selectAllOrderTraces();

    String selectOrdersInfoByCarDriver(String carDriverName);

    String selectBrokenCars();

    String orderCarDriver(String carDriverName, int OrderId) throws DaoException;

    String deleteCarDriver(String carDriverName) throws DaoException;
}
