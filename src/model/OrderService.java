package model;

import utility.Input;

public class OrderService {

	private final Product[] products = new Product[5];
	private final Service[] services = new Service[5];

	int productIndex = 0;
	int serviceIndex = 0;

	public void menuloop() {
		int input;
		do {
			printMenu();
			input = Input.readInt();
			switch ( input ) {
				case 0: break ;
				case 1: orderProduct(productIndex++); break ;
				case 2: orderService(serviceIndex++); break ;
				default: System.out.println("invalid" ); break ;
			}
		} while( input != 0 && (productIndex < 5) && serviceIndex < 5);
		sortProducts();
		sortServices();
		finishOrder() ;
		menuloop();
	}
	
	private void printMenu() {
		System.out.println("Your choice?");
		System.out.println("(0) Finish order");
		System.out.println("(1) Order product");
		System.out.println("(2) Order service");
	}
	
	private void sortProducts() {
		for (int i = 0; i< products.length-1; i++) {
			for (int j = 0; j< products.length-1; j++) {
				if ( products[j+1] != null && products[j+1].getPrice()< products[j].getPrice()) {
					Product temp = products[j + 1] ;
					products[j+1] = products[ j ] ;
					products[j] = temp ;
				}
			}
		}
	}
	private void sortServices() {
		for (int i = 0; i< services.length-1; i++) {
			for (int j = 0; j< services.length-1; j++) {
				if ( services[j+1] != null && services[j+1].getPrice()< services[j].getPrice()) {
					Service temp = services[j + 1] ;
					services[j+1] = services[ j ] ;
					services[j] = temp ;
				}
			}
		}
	}
	private void orderProduct(int index) {
		System.out.println("Name: ");
		String l = Input.readString();
		System.out.println("Unit price (in cents): ");
		int p = Input.readInt();
		System.out.println("Quantity: ");
		int s = Input.readInt();
		products[index] = new Product(l, p, s) ;
	}
	
	private void orderService(int index) {
		System.out.println("model.model.Service type: ");
		String l = Input.readString();
		System.out.println("Number of persons: ");
		int p = Input.readInt();
		System.out.println("Hours: ");
		int s = Input.readInt();
		services[index] = new Service(l, p, s) ;
	}
	
	private void finishOrder() {
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
