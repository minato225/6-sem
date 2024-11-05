package carservice;

import dao.exceptions.DaoException;
import java.util.Scanner;

public class CarManager {
    private final ICarService service;
    private final Scanner in = new Scanner(System.in);

    public CarManager(ICarService service) {
        this.service = service;
    }

    public String executeCommand(int commandNumber) throws DaoException {
        return switch (commandNumber) {
            case 1 -> service.selectAllOrderTraces();
            case 2 -> {
                System.out.print("Введите имя водителя: ");
                yield service.selectOrdersInfoByCarDriver(in.nextLine());
            }
            case 3 -> service.selectBrokenCars();
            case 4 -> OrderCarDriver();
            case 5 -> {
                System.out.print("Введите имя водителя: ");
                yield service.deleteCarDriver(in.nextLine());
            }
            default -> "Unexpected value: " + commandNumber;
        };
    }

    private String OrderCarDriver() throws DaoException {
        System.out.print("Введите имя водителя: ");
        var carDriverName = in.nextLine();

        System.out.print("Введите номер рейса: ");
        int orderNumber = Integer.parseInt(in.nextLine());

        return service.orderCarDriver(carDriverName, orderNumber);
    }
}
