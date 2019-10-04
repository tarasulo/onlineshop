package internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private List<Item> items;
    private Long orderId;
    private Long userId;

    public Bucket(Long userId) {
        this.userId = userId;
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

    public void setId(Long id) {
        this.id = id;
    }
}
