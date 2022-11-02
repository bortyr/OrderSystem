package model;

public abstract interface ItemFactory {

    public Item createProduct(String name, int price, int quantity);

    public Item createService(String name, int hours, int persons);

}
