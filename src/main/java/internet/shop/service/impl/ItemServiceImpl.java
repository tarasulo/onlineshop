package internet.shop.service.impl;

import internet.shop.dao.ItemDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Item;
import internet.shop.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item add(Item item) {

        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {

        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {

        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {

        itemDao.delete(id);
    }

    @Override
    public void delete(Item item) {

        itemDao.delete(item);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}
