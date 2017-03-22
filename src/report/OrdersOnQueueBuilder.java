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
	private OrdersOnQueue order;
	private List<Order> orders;
	private List<Product> products;
	private List<Item> items;
	private List<Inventory> inventory;
	private Location location;
	//private Order order;
	private int locationID=0;
	private String locationType;
//	private List<Movement> movements;
	private Employee employee;
	
	OrdersOnQueueBuilder(){
		
	}
	
	public OrdersOnQueueBuilder orders(List<Order> orders){
		this.orders=orders;
		return this;
	}
	
	public OrdersOnQueueBuilder products(List<Product> products){
		this.products=products;
		return this;
	}
	
	public OrdersOnQueueBuilder items(List<Item> items){
		this.items=items;
		return this;
	}
	
	public OrdersOnQueueBuilder inventory(List<Inventory> inventory){
		this.inventory=inventory;
		return this;
	}
	
	public OrdersOnQueueBuilder employee(Employee employee){
		this.employee=employee;
		return this;
	}
	
	public OrdersOnQueueBuilder location(Location location){
		this.location=location;
		return this;
	}
	
	public OrdersOnQueueBuilder locationID(){
		locationID=location.getLocationID();
		return this;
	}
	
	public OrdersOnQueueBuilder locationType(){
		locationType=location.getLocationType();
		return this;
	}
	
	public OrdersOnQueue build(){
		if(orders==null){
			throw new IllegalStateException("No list of orders");
		}
		if(products==null){
			throw new IllegalStateException("No list of products");
		}
		if(items==null){
			throw new IllegalStateException("No list of items");
		}
		if(inventory==null){
			throw new IllegalStateException("No list of inventory");
		}
		if(employee==null){
			throw new IllegalStateException("No employee");
		}
		if(location==null){
			throw new IllegalStateException("No location");
		}
		if(locationID==0){
			throw new IllegalStateException("No location id");
		}
		if(locationType==null){
			throw new IllegalStateException("No location type");
		}
		return new OrdersOnQueue(this);
	}
	
}
