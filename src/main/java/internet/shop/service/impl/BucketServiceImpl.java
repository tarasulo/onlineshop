package internet.shop.service.impl;

import internet.shop.dao.BucketDao;
import internet.shop.dao.ItemDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.service.BucketService;

import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket add(Bucket bucket) {

        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {

        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {

        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {

        bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItems().clear();
        return update(bucket);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        return bucket.getItems();
    }
}
