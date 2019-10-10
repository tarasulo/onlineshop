package internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", columnDefinition = "INT")
    private Long id;
    @Transient
    private Long userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"))
    private List<Item> items;

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public Order(User user) {
        this.user = user;
        items = new ArrayList<>();
    }

    public Order() {
        items = new ArrayList<>();
    }

    public Order(Long userId) {
        this.userId = userId;
        items = new ArrayList<>();
    }

    public Order(Long userId, List<Item> newItems) {
        this.userId = userId;
        this.items = newItems;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{id=" + id
                + ", userId=" + user + "\n"
                + ", items=" + items + "}";
    }

    public void setItems(List<Item> list) {
        this.items = items;
    }

    public long getUserId() {
        return user.getId();
    }

    public void setId(Long orderId) {
        this.id = orderId;
    }
}
