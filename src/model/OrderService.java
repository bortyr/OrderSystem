package model;

import utility.Input;

public class OrderService {
	@SuppressWarnings("InfiniteLoopStatement")
	public void menuloop() {
		int input;
		Order order = new Order();
		while(true) {
			printMenu();
			input = Input.readInt();
			switch (input) {
				case 0 -> {
					order.finishOrder();
					order = new Order();
				}
				case 1 -> order.orderItem(ItemType.PRODUCT);
				case 2 -> order.orderItem(ItemType.SERVICE);
				default -> System.out.println("invalid");
			}
		}


	}
	private void printMenu() {
		System.out.println("Your choice?");
		System.out.println("(0) Finish order");
		System.out.println("(1) Order product");
		System.out.println("(2) Order service");
	}
}
