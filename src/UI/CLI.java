package UI;

import model.*;
import utility.Input;

import java.util.ArrayList;

public class CLI {
    OrderService orderService = new OrderService();
    private final java.util.Date date = new java.util.Date();
    public java.util.Date getDate() {return date;}

    public void menuloop() {
        int input;

        while (true) {
            printMenu();
            input = Input.readInt();
            switch (input) {
                case 0 -> {
                    orderService.finishOrder();
                    System.out.println("Current Orders:\n");
                    int counter = 0;
                    ArrayList<Order> orders = orderService.ordersDbread();
                    for (var o : orders) {
                        System.out.print("Order[" + counter + "]\n");
                        System.out.print(o.getItem() + "\n");
                        System.out.print("Sum:  " + o.getSum() + "\n");
                        System.out.print("Date: " + getDate() + "\n\n");
                        counter++;
                    }
                    orderService.newOrder();
                }
                case 1 -> CreateItem(ItemType.PRODUCT);
                case 2 -> CreateItem(ItemType.SERVICE);
                case 3 -> orderService.DeleteDb();
                default -> System.out.println("invalid");
            }
        }
    }


    public void CreateItem(ItemType itype) {
        switch (itype) {
            case PRODUCT -> {
                System.out.println("Name: ");
                String name = Input.readString();
                System.out.println("Unit price (in cents): ");
                int price = Input.readInt();
                System.out.println("Quantity: ");
                int quantity = Input.readInt();
                orderService.processOrder(itype, name, price, quantity);
            }
            case SERVICE -> {
                System.out.println("Name: ");
                String name = Input.readString();
                System.out.println("Number of persons: ");
                int persons = Input.readInt();
                System.out.println("Hours: ");
                int hours = Input.readInt();
                orderService.processOrder(itype, name, persons, hours);
            }
        }
    }

    public void printMenu() {
        System.out.println("Your choice?");
        System.out.println("(0) Finish order");
        System.out.println("(1) Order product");
        System.out.println("(2) Order service");
        System.out.println("(3) Remove orders");

    }
}
