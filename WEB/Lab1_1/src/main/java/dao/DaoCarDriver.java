package dao;

import carservice.model.Brand;
import carservice.model.Car;
import carservice.model.CarDriver;
import dao.exceptions.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCarDriver extends Dao {
    private static final String SelectBrokenCars =
            "select * from car " +
                    "where State=false";

    private static final String PutCarForRepair =
            "update car " +
                    "set State=1 " +
                    "where Number=?";

    private static final String AddCarDriver =
            "insert to cardriver(Name, Experience, CarNumber) " +
                    "values(?,?,?)";

    private static final String AddCar =
            "insert to car(Number, Brand, Mileage, State) " +
                    "values(?,?,?,?)";

    private static final String DeleteCarDriver =
            "delete from cardriver " +
                    "where Name=?";

    private static final String DeleteCar =
            "delete from car " +
                    "where Number =" +
                    "   (select CarNumber from cardriver where Name = ?)";

    public ArrayList<Car> selectBrokenCars() {
        compileStatement(SelectBrokenCars);
        executeStatement();

        return getCarsFromResult();
    }

    public String putCarForRepair(int number) {
        compileStatement(PutCarForRepair);
        try {
            preparedStatement.setInt(1, number);
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

    public String addCarDriver(CarDriver carDriver, Car car) {
        compileStatement(AddCar);
        try {
            preparedStatement.setInt(1, car.number());
            preparedStatement.setString(2, car.brand().toString());
            preparedStatement.setInt(3, car.mileage());
            preparedStatement.setBoolean(4, car.state());
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        var carRes = executeUpdateStatement();

        compileStatement(AddCarDriver);
        try {
            preparedStatement.setString(1, carDriver.name());
            preparedStatement.setInt(2, carDriver.experience());
            preparedStatement.setInt(3, car.number());

        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        var carDriverRes = executeUpdateStatement();

        if (carDriverRes == 0 || carRes == 0) return "Some Error!";

        return "Success";
    }

    public String deleteCarDriver(String name) {
        compileStatement(DeleteCar);
        try {
            preparedStatement.setString(1, name);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        var deleteCarRes = executeUpdateStatement();

        compileStatement(DeleteCarDriver);
        try {
            preparedStatement.setString(1, name);
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't put car driver name to query.", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        var deleteCarDriver = executeUpdateStatement();

        if (deleteCarRes == 0 || deleteCarDriver == 0) return "Delete error!";
        return "Success";
    }

    private ArrayList<Car> getCarsFromResult() {
        var cars = new ArrayList<Car>();

        try {
            while (result.next())
                cars.add(getCarFromResult());
        } catch (SQLException throwable) {
            try {
                throw new DaoException("Error! Can't get data from ResultSet!", throwable);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return cars;
    }

    private Car getCarFromResult() throws SQLException {
        var number = result.getInt("Number");
        var brand = Brand.valueOf(result.getString("Brand"));
        var mileage = result.getInt("Mileage");
        var state = result.getBoolean("State");

        return new Car(number, brand, mileage, state);
    }
}
