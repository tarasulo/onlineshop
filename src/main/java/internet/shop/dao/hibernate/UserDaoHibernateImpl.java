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
        return null;
    }

    @Override
    public User get(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
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
        }
    }

    @Override
    public User login(String login, String psw)
            throws AuthenticationException {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
            if (user.getPassword().equals(HashUtil.hashPassword(psw, user.getSalt()))) {
                // user.setRoles(getRoles(user.getId()));
                return user;
            }
        }
        if (user == null) {
            throw new AuthenticationException("incorrect username or password");
        }
        return user;
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE token=:token");
            query.setParameter("token", token);
            List<User> list = query.list();
            return list.stream().findFirst();
        }
    }

    @Override
    public byte[] getSaltByLogin(String login) {
        return new byte[0];
    }

}
