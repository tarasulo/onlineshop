package internet.shop.dao.jdbc;

import internet.shop.dao.BucketDao;
import internet.shop.dao.UserDao;
import internet.shop.lib.Dao;
import internet.shop.lib.Inject;
import internet.shop.model.Bucket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    private static String queryCreateBucket = "INSERT INTO buckets (user_id) VALUES (?);";
    private static String queryGetBucket = "SELECT * FROM buckets WHERE bucket_id = ?;";
    private static String queryUpdateBucket = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
    private static String queryRemoveBucket = "DELETE FROM buckets WHERE bucket_id = ?;";
    private static String queryGetBucketByUserId = "SELECT * FROM buckets WHERE user_id = ?;";

    @Inject
    private static UserDao userDao;

    private static Bucket bucket = null;
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        try (PreparedStatement statementBuckets = connection.prepareStatement(
                queryCreateBucket, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementBuckets.setLong(1, bucket.getUserId());
            statementBuckets.executeUpdate();
            ResultSet generatedKeys = statementBuckets.getGeneratedKeys();
            generatedKeys.next();
            Long bucketId = generatedKeys.getLong(1);
            bucket.setId(bucketId);
        } catch (SQLException e) {
            logger.error("Can't create bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryGetBucket)) {
            statementBuckets.setLong(1, bucketId);
            ResultSet resultSet = statementBuckets.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                bucket = new Bucket(userId);
                bucket.setId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryUpdateBucket)) {
            statementBuckets.setLong(1, bucket.getUserId());
            statementBuckets.setLong(2, bucket.getId());
            statementBuckets.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryRemoveBucket)) {
            statementBuckets.setLong(1, id);
            statementBuckets.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket", e);
        }
        return get(bucket.getId());
    }

    @Override
    public Bucket getBucketByUserId(Long userId) {
        try (PreparedStatement statementBuckets = connection
                .prepareStatement(queryGetBucketByUserId)) {
            statementBuckets.setLong(1, userId);
            ResultSet resultSet = statementBuckets.executeQuery();
            while (resultSet.next()) {
                Long bucketId = resultSet.getLong("bucket_id");
                bucket = new Bucket(userId);
                bucket.setId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket", e);
        }
        return bucket;
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
    }
}
