package adapter.gateway;

import entities.Order;
import usecase.OrderStructure;

import java.util.ArrayList;

public class OrderProvider implements OrderRepository {
    // orders - collection to store orders
    private final ArrayList<Order> orders = new ArrayList<>();

    // create() - add order to collection of orders
    public void create(OrderStructure orderStructure) {
        orders.add(orderStructure.get());
    }

    // read() - return collection of orders
    public ArrayList<Order> read() {
        return orders;
    }

    // update() - update order
    // TODO: implement update order

    // delete() - clears all orders
    public void delete() {
        orders.clear();
    }

}
