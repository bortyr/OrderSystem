package adapter.gateway;

import entities.Order;
import usecase.OrderStructure;

import java.util.ArrayList;

public interface OrderRepository {
    void create(OrderStructure orderStructure);
    ArrayList<Order> read();
    //TODO: include update in repository interface
    //void update(Order order, int id);
    void delete();
}
