package internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static long idGenerator = 0;
    private Long id;
    private final Long userId;
    private List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
        id = idGenerator++;
    }

    public Order(Long userId) {
        this.userId = userId;
        items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{id=" + id
                + ", userId=" + userId + "\n"
                + ", items=" + items + "}";
    }

    public void setId(Long orderId) {
        this.id = id;
    }

    public void setItems(List<Item> list) {
        this.items = items;
    }
}
