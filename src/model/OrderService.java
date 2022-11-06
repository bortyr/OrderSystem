package model;

import utility.Input;

public class OrderService {
	@SuppressWarnings("InfiniteLoopStatement")
	public void menuloop() {
		int input;
		Order order = new Order();
		OrderProvider ordersDb = new OrderProvider();
		while(true) {
			printMenu();
			input = Input.readInt();
			switch (input) {
				case 0 -> {
					order.finishOrder();
					// TODO: sanitize input bc empty orders can be created
					ordersDb.create(order);
					// Just for display START-> rm when CLI & GUI done
					System.out.println("Current Orders:\n");
					int counter = 0;
					var orders = ordersDb.read();
					for (var o : orders)
					{
						System.out.print("Order[" + counter  + "]\n");
						System.out.print("Sum:  " + o.getSum() + "\n");
						System.out.print("Date: " + o.getDate() + "\n\n");
						counter++;
					}
					// Just for display END -> rm when CLI & GUI done
					order = new Order();
				}
				case 1 -> order.orderItem(ItemType.PRODUCT);
				case 2 -> order.orderItem(ItemType.SERVICE);
				case 3 -> ordersDb.delete();
				default -> System.out.println("invalid");
			}
		}


	}
	private void printMenu() {
		System.out.println("Your choice?");
		System.out.println("(0) Finish order");
		System.out.println("(1) Order product");
		System.out.println("(2) Order service");
		System.out.println("(3) Remove orders");
	}
}
