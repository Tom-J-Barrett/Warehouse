package inventory;

import java.util.ArrayList;
import java.util.List;

import location.InventoryLocation;
import location.Location;

public class Order {

	private int id;
	private String shipTo;
	private Location location;
	private List<Product> products;

	public Order(int id, String shipTo) {
		this(new ArrayList<>(), id, shipTo);
	}

	public Order(List<Product> products, int id, String shipTo) {
		this.products = products;
		this.id = id;
		this.shipTo = shipTo;
		location = new InventoryLocation(123, "1");
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public int getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Order [products=" + products + ", orderID=" + id + ", shipTo=" + shipTo + "]";
	}
}
