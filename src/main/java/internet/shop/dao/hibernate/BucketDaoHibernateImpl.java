package internet.shop.dao.hibernate;

import internet.shop.dao.BucketDao;
import internet.shop.lib.Dao;
import internet.shop.lib.Inject;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.service.ItemService;
import internet.shop.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);
    @Inject
    private static ItemService itemService;

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
        Bucket bucket = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user.id=:id");
            query.setParameter("id", id);
            bucket = (Bucket) query.uniqueResult();
        }
        if (bucket == null) {
            logger.error("Can't get bucket by id!");
            throw new RuntimeException("Can't get bucket by id!");
        }
        return bucket;
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Bucket bucket = get(bucketId);
            Item removedItem = bucket.getItems()
                    .stream()
                    .filter(i -> i.getId().equals(itemId))
                    .findFirst().get();
            bucket.getItems().remove(removedItem);
            update(bucket);
        } catch (Exception e) {
            logger.error("Can’t delete item with id " + itemId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Bucket addItem(Long bucketId, Long itemId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = get(bucketId);
            List<Item> itemList = bucket.getItems();
            Item item = itemService.get(itemId);
            itemList.add(item);
            update(bucket);
            return bucket;
        }
    }

    public List<Item> getAllItems(Long bucketId) {
        return get(bucketId).getItems();
    }
}
