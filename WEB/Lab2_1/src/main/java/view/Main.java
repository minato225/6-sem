package view;

import carservice.CarManager;
import carservice.MySqlCarService;
import dao.exceptions.DaoException;

import java.util.Scanner;

public class Main {
    public static void main(String... args) throws DaoException {

        var in = new Scanner(System.in);
        var service = new MySqlCarService();
        var carManager = new CarManager(service);

        while (true) {

            System.out.println("""
                    Выберите действие:
                    1) Вывести список рейсов.
                    2) Вывести информацию обо всех рейсах заданного водителя.
                    3) Вывести список автомобилей, находящихся в ремонте.
                    4) Назначить водителя на рейс.                    
                    5) Удалить водителя и его машину.
                    0) Выход.""");

            int choice;
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Не верный ввод!\n");
                continue;
            }

            if (choice == 0) break;

            var result = carManager.executeCommand(choice);
            System.out.println(result);
        }

        System.out.println("Выход...");
    }
}
