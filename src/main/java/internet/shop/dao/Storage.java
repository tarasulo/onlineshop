package internet.shop.dao;

import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.model.Order;
import internet.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
}
