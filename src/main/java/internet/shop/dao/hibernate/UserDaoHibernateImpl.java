package internet.shop.dao.hibernate;

import internet.shop.dao.UserDao;
import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Dao;
import internet.shop.model.User;
import internet.shop.util.HashUtil;
import internet.shop.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User add(User user) {
        Long userId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
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
        user.setId(userId);
        return user;
    }

    @Override
    public User get(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        } catch (Exception e) {
            logger.error("Can't get user by id=" + id, e);
        }
        return null;
    }

    @Override
    public User update(User userDao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(userDao);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update User " + userDao, e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return userDao;
    }

    @Override
    public void delete(Long id) {
        User user = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete user with id " + id, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        } catch (Exception e) {
            logger.error("Can't get all users", e);
        }
        return null;
    }

    @Override
    public User login(String login, String psw)
            throws AuthenticationException {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
            if (user == null) {
                throw new AuthenticationException("incorrect username or password");
            }
            if (user.getPassword().equals(HashUtil.hashPassword(psw, user.getSalt()))) {
                return user;
            }
        } catch (Exception e) {
            logger.error("incorrect username or password", e);
            throw new AuthenticationException("incorrect username or password");
        }
        return user;
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where token=:token");
            query.setParameter("token", token);
            User user = (User) query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Can't get user by token", e);
        }
        return Optional.empty();
    }

    @Override
    public byte[] getSaltByLogin(String login) {
        return new byte[0];
    }

}
