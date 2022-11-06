package model;

import java.util.ArrayList;

public interface OrderRepository {
    void create(Order order);
    ArrayList<Order> read();
    //TODO: include update in repository interface
    //void update(Order order, int id);
    void delete();
}
