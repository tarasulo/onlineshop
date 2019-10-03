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

    @Inject
    private static UserDao userDao;
    private static Bucket bucket = null;
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String bucketQuery = "INSERT INTO buckets (user_id) VALUES (?);";
        try (PreparedStatement statementBuckets = connection.prepareStatement(
                bucketQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
        String queryBuckets = "SELECT * FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryBuckets)) {
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
        String queryBucktes =
                "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryBucktes)) {
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
        String queryBucktes = "DELETE FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryBucktes)) {
            statementBuckets.setLong(1, id);
            statementBuckets.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket", e);
        }
        return get(bucket.getId());
    }

    @Override
    public Bucket getBucketByUserId(Long userId) {
        String queryBucktes = "SELECT * FROM buckets WHERE user_id = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryBucktes)) {
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
}
