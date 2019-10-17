package internet.shop.dao.hibernate;

import internet.shop.dao.RoleDao;
import internet.shop.lib.Dao;
import internet.shop.model.Role;
import internet.shop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoHibernateImpl.class);

    @Override
    public Role get(Role.RoleName roleName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Role where roleName=:name");
            query.setParameter("name", roleName);
            return (Role) query.getSingleResult();
        } catch (Exception e) {
            logger.error("Can't get role by name: " + roleName, e);
        }
        return null;
    }
}
