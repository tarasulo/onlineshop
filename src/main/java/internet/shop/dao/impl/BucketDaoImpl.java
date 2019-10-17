package internet.shop.dao.impl;

import internet.shop.dao.BucketDao;
import internet.shop.dao.Storage;
import internet.shop.lib.Dao;
import internet.shop.model.Bucket;

import java.util.NoSuchElementException;
import java.util.Objects;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets
                .stream().filter(b -> Objects.equals(b.getId(), bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Objects.equals(Storage.buckets.get(i).getId(), bucket.getId())) {
                Storage.buckets.set(i, bucket);
                return bucket;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = get(id);
        Storage.buckets.removeIf((b) -> Objects.equals(b.getId(), id));
        return bucket;
    }

    @Override
    public Bucket getBucketByUserId(Long id) {
        return null;
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {}
}
