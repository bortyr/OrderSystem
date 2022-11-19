package usecase;

import entities.Order;

public interface OrdersAccess {
    void create(Order order);
    Order get();
}
