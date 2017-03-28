package inventory;

import java.util.ArrayList;
import java.util.List;

import location.InventoryLocation;
import location.Location;

public class Order{
	private List<Product> products;
	private int orderID;
	private String shipTo;
	private Location location;
	
	public Order(){
		
	}
	
	public Order(List<Product> products2, int orderID, String shipTo){
		this.products=products2;
		this.orderID=orderID;
		this.shipTo=shipTo;
		location=new InventoryLocation(123,"1");
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void setProducts(ArrayList<Product> products){
		this.products=products;
	}
	
	public String getShipTo(){
		return shipTo;
	}
	
	public void setShipTo(String shipTo){
		this.shipTo=shipTo;
	}
	
	public int getOrderID(){
		return orderID;
	}
	

	public Location getLocation(){
		return location;
	}

	@Override
	public String toString() {
		return "Order [products=" + products + ", orderID=" + orderID + ", shipTo=" + shipTo + "]";
	}
}
