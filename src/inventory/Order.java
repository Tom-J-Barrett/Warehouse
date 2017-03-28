package inventory;

import java.util.ArrayList;
import java.util.List;

public class Order{
	private List<Product> products;
	private int orderID;
	private String shipTo;
	
	public Order(){
		
	}
	
	public Order(List<Product> products2, int orderID, String shipTo){
		this.products=products2;
		this.orderID=orderID;
		this.shipTo=shipTo;
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
	
	@Override
	public String toString() {
		return "Product [items=" + items + ", productName=" + productName + ", productID=" + productID + "]";
	}
}
