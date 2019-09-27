package internet.shop.dao;

import internet.shop.model.Item;

import java.util.List;

public interface ItemDao {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    Item delete(Item item);

    List<Item> getAll();
}
