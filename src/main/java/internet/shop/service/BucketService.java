package internet.shop.service;

import internet.shop.model.Bucket;
import internet.shop.model.Item;

import java.util.List;

public interface BucketService {

    Bucket add(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);
}
