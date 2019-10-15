package internet.shop.dao.hibernate;

import internet.shop.dao.OrderDao;
import internet.shop.lib.Dao;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;
import internet.shop.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Long orderId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setId(orderId);
        return order;
    }

    @Override
    public Order get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, id);
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public Order delete(Long id) {
        Transaction transaction = null;
        Order order = get(id);
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    public List<Order> getOrdersForUser(Long id) {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where user.id=:id");
            query.setParameter("id", id);
            orders = query.getResultList();
        }
        return orders;
    }

    @Override
    public Order complite(List<Item> items, User user) {
        Order order = new Order(user, items);
        return create(order);
    }
}
