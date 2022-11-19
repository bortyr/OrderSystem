package usecase;

import entities.ItemType;
import entities.Order;

public class OrderService {

    Order order = new Order();

    public void processOrder(ItemType type, String name, int value1, int value2)
    {
        order.orderItem(type, name, value1, value2);
    }

    public void finishOrder() {
        order.sorting();
    }

    public void newOrder(){
        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

}


