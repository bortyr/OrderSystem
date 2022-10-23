package model;

public class Service {

	private final String name;
	private final int hours;
	private final int persons;

	public Service(String n, int p, int s) {
		name = n;
		hours = s;
		persons = p;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return 1242 * hours * persons;
	}

	public void print() {
		System.out.println(persons + " persons for " + hours + "h of " + getName());
	}
}
