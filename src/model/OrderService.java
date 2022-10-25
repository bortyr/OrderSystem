package model;

import utility.Input;
import java.util.ArrayList; // import the ArrayList class
import java.util.Comparator;

class Order {

	public ArrayList<Product> products = new ArrayList<>();
	public ArrayList<Service> services = new ArrayList<>();

	public void orderProduct() {
		System.out.println("Name: ");
		String l = Input.readString();
		System.out.println("Unit price (in cents): ");
		int p = Input.readInt();
		System.out.println("Quantity: ");
		int s = Input.readInt();
		Product product = new Product(l, p, s) ;
		products.add(product);
	}
	public void orderService() {
		System.out.println("Name: ");
		String l = Input.readString();
		System.out.println("Number of persons: ");
		int p = Input.readInt();
		System.out.println("Hours: ");
		int s = Input.readInt();
		Service service = new Service(l, p, s) ;
		services.add(service);
	}
	public void finishOrder() {

		products.sort(Comparator.comparing(Product::getPrice));
		services.sort(Comparator.comparing(Service::getPrice));

		int sum = 0;
		for (Product product : products) {
			if (product != null) {
				System.out.println(product + " = " + formatPrice(product.getPrice()));
				sum += product.getPrice();
			}
		}
		for (Service service : services) {
			if (service != null) {
				service.print();
				System.out.println(" = " + formatPrice(service.getPrice()));
				sum += service.getPrice();
			}
		}
		System.out.println("Sum: "+ formatPrice(sum));
	}
	private String formatPrice(int priceInCent) {
		return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
				+ priceInCent % 100 + " EUR";
	}

}

public class OrderService {
	private final Order order = new Order();
	public void menuloop() {
		int input;
		do {
			printMenu();
			input = Input.readInt();
			switch ( input ) {
				case 0: break ;
				case 1: order.orderProduct(); break ;
				case 2: order.orderService(); break ;
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
