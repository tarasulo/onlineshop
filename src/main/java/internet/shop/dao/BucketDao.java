package internet.shop.dao;

import internet.shop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket getBucketByUserId(Long id);

    void deleteItem(Long bucketId, Long itemId);
}
