package model;

public class Service implements Item {

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
		int rate = 1242;
		return rate * hours * persons;
	}

}
