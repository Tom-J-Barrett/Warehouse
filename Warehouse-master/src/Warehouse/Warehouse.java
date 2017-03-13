package Warehouse;
import Inventory.Order;
import Inventory.Item;
import Inventory.Product;
import java.util.*;


public class Warehouse{
	private List<Order> orders;
	private List<Item> items;
	private List<Product> products;
	private Item itemToReceive;
	
	Warehouse(){
		
	}
	
	Warehouse(ArrayList<Order> orders, ArrayList<Item> items, ArrayList<Product> products, Item itemToReceive){
		this.orders=orders;
		this.products=products;
		this.items=items;
		this.itemToReceive=itemToReceive;
	}
	
	public void placeStock(){
		
	}
	
	public void setOrders(ArrayList<Order> orders)
	{
		this.orders=orders;
	}
	
	public ArrayList<Order> getOrders(){
		return (ArrayList<Order>) orders;
	}
	
	public void setProducts(ArrayList<Product> products)
	{
		this.products=products;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void setItems(ArrayList<Item> items)
	{
		this.items=items;
	}
	
	public ArrayList<Item> getItems(){
		return (ArrayList<Item>) items;
	}
	
	public void setItemToReceive(Item itemToReceive)
	{
		this.itemToReceive=itemToReceive;
	}
	
	public Item getItemToReceive(){
		return itemToReceive;
	}
	
	
}
