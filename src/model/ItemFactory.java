package model;

public interface ItemFactory {

    Item createProduct(String name, int price, int quantity);

    Item createService(String name, int hours, int persons);

}
