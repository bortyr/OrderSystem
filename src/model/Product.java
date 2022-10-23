package model;

public class Product {

	private final String name;
	private final int unitPrice;
	private final int quantity;

	public Product(String name, int unitPrice, int quantity) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return unitPrice * quantity;
	}

	@Override
	public String toString() {
		return quantity + " * " + getName();
	}
}
