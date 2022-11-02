package model;

import utility.Input;

import java.util.ArrayList;
import java.util.Comparator;

interface Item {
	String getName();
	int getPrice();
}
class Order {
	public ArrayList<Item> items = new ArrayList<>();
	private final java.util.Date date = new java.util.Date();
	private int sum = 0;
	private final ItemFactory Factory = new SimpleItemFactory();
	public java.util.Date getDate() {return date;}

	public void orderItem(String type) {
		switch (type) {
			case "PRODUCT" -> {
				System.out.println("Name: ");
				String name = Input.readString();
				System.out.println("Unit price (in cents): ");
				int price = Input.readInt();
				System.out.println("Quantity: ");
				int quantity = Input.readInt();
				Item product = Factory.createProduct(name, price, quantity);
				items.add(product);
			}
			case "SERVICE" -> {
				System.out.println("Name: ");
				String name = Input.readString();
				System.out.println("Number of persons: ");
				int persons = Input.readInt();
				System.out.println("Hours: ");
				int hours = Input.readInt();
				Item service = Factory.createService(name, hours, persons);
				items.add(service);
			}
		}
	}

	public int getSum() {
		for (Item item : items) {
			if (item != null) {
				System.out.println(item.getName() + " = " + formatPrice(item.getPrice()));
				sum += item.getPrice();
			}
		}
		return sum;
	}
	private String formatPrice(int priceInCent) {
		return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
				+ priceInCent % 100 + " EUR";
	}
	public void finishOrder() {
		items.sort(Comparator.comparing(Item::getPrice));
		System.out.println("Sum: "+ formatPrice(getSum()));
		System.out.println("Date: "+ getDate());
	}
}

public class OrderService {
	public void menuloop() {
		Order order = new Order();
		int input;
		do {
			printMenu();
			input = Input.readInt();
			switch ( input ) {
				case 0: break ;
				case 1: order.orderItem("PRODUCT"); break ;
				case 2: order.orderItem("SERVICE"); break ;
				default: System.out.println("invalid" ); break ;
			}
		} while( input != 0);
		order.finishOrder() ;
		menuloop();
	}
	private void printMenu() {
		System.out.println("Your choice?");
		System.out.println("(0) Finish order");
		System.out.println("(1) Order product");
		System.out.println("(2) Order service");
	}
}
