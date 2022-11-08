package model;

import utility.Input;
import java.util.ArrayList;
import java.util.Comparator;

public class Order {
    public ArrayList<Item> items = new ArrayList<>();

    private int sum = 0;
    private String FormatedSum = "0";
    private final ItemFactory Factory = new SimpleItemFactory();


    public void orderItem(ItemType itype, String name, int value1, int value2) {
        switch (itype) {
            case PRODUCT -> {
                Item product = Factory.createProduct(name, value1, value2);
                items.add(product);
            }
            case SERVICE -> {
                Item service = Factory.createService(name, value1, value2);
                items.add(service);
            }
        }
    }

    public String getItem() {
        String string1 = "0";
        for (Item item : items) {
            if (item != null) {
                string1 = item.getName() + " = " + formatPrice(item.getPrice());
            }
        }
        return string1;
    }

    public String getSum() {
        for (Item item : items) {
            if (item != null) {
                sum += item.getPrice();
                FormatedSum = formatPrice(sum);
            }
        }
        return FormatedSum;
    }
    private String formatPrice(int priceInCent) {
        return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
                + priceInCent % 100 + " EUR";
    }

    // finishOrder() - sort the order according to price

    public void sorting() {
        items.sort(Comparator.comparing(Item::getPrice));
    }
}