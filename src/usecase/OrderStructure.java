package usecase;

import entities.Order;

public class OrderStructure implements OrdersAccess {

    private Order order = new Order();

    // create() - create order
    public void create(Order o) {
       order = o;
    }

    // get() - return order
    public Order get() {
        return order;
    }
}
