package internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static long idGenerator = 0;
    private final Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private List<Order> orders;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        id = idGenerator++;
        orders = new ArrayList<>();
    }

    public User(String name) {
        id = idGenerator++;
        this.name = name;
        orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{id=" + id
                + ", name=" + name + "\n"
                + ", orders=" + orders +  "}";
    }
}
