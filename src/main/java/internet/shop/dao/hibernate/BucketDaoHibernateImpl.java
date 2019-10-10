package internet.shop.dao.hibernate;

import internet.shop.dao.BucketDao;
import internet.shop.model.Bucket;
import internet.shop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        Transaction transaction = null;
        Long bucketId = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create bucket", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setId(bucketId);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = session.get(Bucket.class, id);
            return bucket;
        } catch (Exception e) {
            logger.error("Can't get bucket by id=" + id);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update bucket id=" + bucket.getId(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            bucket = get(id);
            session.delete(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete bucket id=" + id);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket getBucketByUserId(Long id) {
        return null;
    }
}
