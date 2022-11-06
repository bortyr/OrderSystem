// Hidden loop for main menu

package model;

import utility.Input;

public class OrderService {
	public void menuloop() {
		Order order = new Order();
		int input;
		do {
			printMenu();
			input = Input.readInt();
			switch ( input ) {
				case 0: break ;
				case 1: order.orderItem(ItemType.PRODUCT); break ;
				case 2: order.orderItem(ItemType.SERVICE); break ;
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
