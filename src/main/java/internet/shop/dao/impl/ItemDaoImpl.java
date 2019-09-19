package internet.shop.dao.impl;

import internet.shop.dao.ItemDao;
import internet.shop.dao.Storage;
import internet.shop.lib.Dao;
import internet.shop.model.Item;

import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find file with id " + id));
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return item;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Storage.items.removeIf((i) -> i.getId().equals(id));
        return item;
    }

    @Override
    public Item delete(Item item) {
        delete(item.getId());
        return item;
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
