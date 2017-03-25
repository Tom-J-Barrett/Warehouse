package report;

import java.util.*;
import inventory.Order;
import inventory.Product;
import inventory.Item;
import inventory.Inventory;
import location.Location;
import movement.Movement;
import employee.Employee;
import report.OrdersOnQueue;

public final class OrdersOnQueueBuilder {
	public OrdersOnQueue order;
	public List<Order> orders;
	public List<Product> products;
	public List<Item> items;
	public List<Inventory> inventory;
	public Location location;
	//private Order order;
	public int locationID=0;
	public String locationType;
//	private List<Movement> movements;
	//public Employee employee;
	
	
	public OrdersOnQueueBuilder orders(List<Order> orders){
		System.out.println("1");
		this.orders=orders;
		return this;
	}
	
	public OrdersOnQueueBuilder products(List<Product> products){
		System.out.println("2");
		this.products=products;
		return this;
	}
	
	public OrdersOnQueueBuilder items(List<Item> items){
		System.out.println("3");
		this.items=items;
		return this;
	}
	
	public OrdersOnQueueBuilder inventory(List<Inventory> inventory){
		System.out.println("4");
		this.inventory=inventory;
		return this;
	}
	
	/*public OrdersOnQueueBuilder employee(Employee employee){
		this.employee=employee;
		return this;
	}*/
	
	public OrdersOnQueueBuilder location(Location location){
		this.location=location;
		return this;
	}
	
	/*public OrdersOnQueueBuilder locationID(){
		locationID=location.getLocationID();
		return this;
	}
	
	public OrdersOnQueueBuilder locationType(){
		locationType=location.getLocationType();
		return this;
	}*/
	
	public OrdersOnQueue build(){
		if(orders==null){
			System.out.println("No list of orders");
		}
		if(products==null){
			System.out.println("No list of products");
		}
		if(items==null){
			System.out.println("No list of items");
		}
		if(inventory==null){
			System.out.println("No list of inventory");
		}
		if(location==null){
			System.out.println("No location");
		}
		/*if(locationID==0){
			throw new IllegalStateException("No location id");
		}
		if(locationType==null){
			throw new IllegalStateException("No location type");
		}*/
		return new OrdersOnQueue(this);
	}
	
}
