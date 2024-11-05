package dao;

import dao.exceptions.DaoException;
import entity.Car;
import entity.Cardriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoCarDriver extends Dao {

    private static final Logger logger = LogManager.getLogger();

    public List<Car> selectBrokenCars() {
        List<Car> cars;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            cars = manager
                    .createNamedQuery("Car.SelectBrokenCars", Car.class)
                    .getResultList();
            logger.info("Broken cars was selected");
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
        return cars;
    }

    public String deleteCarDriver(String name) throws DaoException {

        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = factory.createEntityManager();
            transaction = manager.getTransaction();

            var carDriver = manager
                    .createNamedQuery("Cardriver.DeleteCarDriver", Cardriver.class)
                    .setParameter("Name", name)
                    .getSingleResult();

            transaction.begin();
            manager.remove(carDriver);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw new DaoException("failed to order cardriver", e);
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }

        return "Success";
    }

    @Override
    public void close() throws Exception {

    }
}
