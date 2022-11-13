package usecase;

import entities.ItemType;
import entities.Order;
import adapter.gateway.OrderProvider;

import java.util.ArrayList;


public class OrderService {

    Order order = new Order();
    OrderProvider ordersDb = new OrderProvider();

    public void processOrder(ItemType type, String name, int value1, int value2)
    {
        order.orderItem(type, name, value1, value2);
    }

    public void DeleteDb(){
        ordersDb.delete();
    }

    public void finishOrder() {
        order.sorting();
        ordersDb.create(order);
    }

    public ArrayList<Order> ordersDbread(){
        return ordersDb.read();
    }
    public void newOrder(){
        order = new Order();
    }


}


