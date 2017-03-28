package inventory;

import java.util.*;

public class Product{
	private List<Item> items;
	private String productName;
	private int productID;
	
	public Product(){
		
	}
	
	public Product(ArrayList<Item> items, String productName, int productID){
		this.items=items;
		this.productName=productName;
		this.productID=productID;
	}
	
	public ArrayList<Item> getItems(){
		return (ArrayList<Item>) items;
	}
	
	public void setItems(ArrayList<Item> items){
		this.items=items;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductName(String productName){
		this.productName=productName;
	}
	
	public int getProductID(){
		return productID;
	}
	
	@Override
	public String toString() {
		return "Product [items=" + items + ", productName=" + productName + ", productID=" + productID + "]";
	}
}
