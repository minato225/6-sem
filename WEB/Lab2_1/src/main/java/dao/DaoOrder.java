package dao;

import dao.exceptions.DaoException;
import entity.Cardriver;
import entity.Carorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoOrder extends Dao {

    private static final Logger logger = LogManager.getLogger();

    public List<Carorder> selectAllOrderTraces() {
        List<Carorder> orders;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            orders = manager
                    .createNamedQuery("Carorder.SelectAllOrderTraces", Carorder.class)
                    .getResultList();
            logger.info("All Order Traces was selected");
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
        return orders;
    }

    public Carorder selectOrdersInfoByCarDriver(String carDriverName) {
        Carorder order;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            order = manager
                    .createNamedQuery("Carorder.SelectOrdersInfoByCarDriver", Carorder.class)
                    .setParameter("Name", carDriverName)
                    .getSingleResult();
            logger.info("Orders Info By CarDriver was selected");
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
        return order;
    }

    public String orderCarDriver(String carDriverName, int OrderId) throws DaoException {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = factory.createEntityManager();
            transaction = manager.getTransaction();

            var order = manager.find(Carorder.class, OrderId);
            var carDriver = manager
                    .createNamedQuery("Carorder.CardriverIdByName", Cardriver.class)
                    .setParameter("Name", carDriverName)
                    .getSingleResult();

            order.setCardriverByDriverId(carDriver);

            transaction.begin();
            manager.merge(order);
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
