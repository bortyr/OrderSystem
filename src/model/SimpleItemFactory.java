package model;

public class SimpleItemFactory implements ItemFactory {

    public Item createProduct(String name, int price, int quantity)
    {
        return new Product(name, price, quantity);
    }

    public Item createService(String name, int hours, int persons)
    {
        return new Service(name, hours, persons);
    }
}
