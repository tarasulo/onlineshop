package internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private static long idGenerator = 0;
    private List<Item> items;
    private Long orderId;
    private Long userId;

    public Bucket(Long userId) {
        this.userId = userId;
        orderId = idGenerator++;
        items = new ArrayList<>();
    }

    public Long getId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
