package carservice;

import carservice.model.Brand;
import carservice.model.Car;
import carservice.model.CarDriver;
import carservice.model.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CarManager {
    private final ICarService service;
    private final Scanner in = new Scanner(System.in);

    public CarManager(ICarService service) {
        this.service = service;
    }

    public String executeCommand(int commandNumber) {
        return switch (commandNumber) {
            case 1 -> service.selectAllOrderTraces();
            case 2 -> {
                System.out.print("Введите имя водителя: ");
                yield service.selectOrdersInfoByCarDriver(in.nextLine());
            }
            case 3 -> service.selectBrokenCars();
            case 4 -> OrderCarDriver();
            case 5 -> {
                System.out.print("Введите номер машины: ");
                yield service.putCarForRepair(Integer.parseInt(in.nextLine()));
            }
            case 6 -> AddCarDriver();
            case 7 -> AddOrder();
            case 8 -> {
                System.out.print("Введите имя водителя: ");
                yield service.deleteCarDriver(in.nextLine());
            }
            case 9 -> {
                System.out.print("Введите номер заявки: ");
                yield service.deleteOrder(Integer.parseInt(in.nextLine()));
            }
            case 10 -> {
                System.out.print("Введите имя водителя: ");
                yield service.finishOrder(in.nextLine());
            }
            default -> "Unexpected value: " + commandNumber;
        };
    }

    private String OrderCarDriver() {
        System.out.print("Введите имя водителя: ");
        var carDriverName = in.nextLine();

        System.out.print("Введите номер рейса: ");
        int orderNumber = Integer.parseInt(in.nextLine());

        return service.orderCarDriver(carDriverName, orderNumber);
    }

    private String AddCarDriver() {
        System.out.print("Введите имя водителя: ");
        var name = in.nextLine();

        System.out.print("Введите опыт водителя: ");
        var experience = Integer.parseInt(in.nextLine());

        System.out.print("Введите номер машины: ");
        var carNumber = Integer.parseInt(in.nextLine());

        System.out.print("Введите макру машины: ");
        var carBrand = Brand.valueOf(in.nextLine());

        System.out.print("Введите пробег машины: ");
        var carMileage = Integer.parseInt(in.nextLine());

        System.out.print("Введите состояние машины (0/1): ");
        var carState = Boolean.parseBoolean(in.nextLine());

        var carDriver = new CarDriver(name, experience);
        var car = new Car(carNumber, carBrand, carMileage, carState);
        return service.addCarDriver(carDriver, car);
    }

    private String AddOrder() {
        System.out.print("Введите Нужный бренд машины: ");
        var brand = Brand.valueOf(in.nextLine());

        System.out.print("Введите Нужный прогон машины: ");
        var mileage = Integer.parseInt(in.nextLine());

        System.out.print("Введите куда прислать машину: ");
        var departure = in.nextLine();

        System.out.print("Введите пункт названичения: ");
        var destination = in.nextLine();

        System.out.print("Введите время отправки (hh:mm)сегодня: ");
        Date startTime;
        try {
            startTime = new SimpleDateFormat("hh:mm").parse(in.nextLine());
        } catch (ParseException e) {
            return "Не верный формат времени. (hh:mm)";
        }

        var carOrder = new Order(0, brand, mileage, false, departure, destination, startTime);
        return service.addOrder(carOrder);
    }
}
