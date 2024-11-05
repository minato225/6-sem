package view;

import carservice.CarManager;
import carservice.MySqlCarService;

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
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
                    5) Поставить автомобиль на ремонт.
                    6) Добавить нового водителя и его машину.
                    7) Добавить новый заказ.
                    8) Удалить водителя и его машину.
                    9) Удалить заказ.
                    10) Сделать отметку о выполнении рейса.
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
