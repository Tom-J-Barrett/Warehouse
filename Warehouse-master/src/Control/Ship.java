package Control;
import java.util.*;

import java.io.*;
import Shipment.Shipment;
import Location.Location;
import Location.ShipLocation;
import Location.ShipLocation;
import Inventory.Order;
import Inventory.Product;

public class Ship {
	private Shipment shipment;
	private List<Order> orders;
	private Location location;
	private int locationID,orderID;
	private Order order1;
	private Order order2;
	private Order orderToShip;
	private List<Product> products;
	private List<Product> products2;
	
	public Ship(){
		location=new ShipLocation();
		orders=new ArrayList<Order>();
		getInfoFromDatabase();
		displayOrders();
		shipOrder();
	}
	
	public void shipOrder(){
		shipment=new Shipment(location,orderToShip,orderToShip.getShipTo());
		updateDatabase(shipment);
	}
	
	public void getLocation(){
		
	}
	
	public void createOrderList(){
		order1=new Order(products,1,"address");
		order2= new Order(products2,2,"address2");
		orders.add(order1);
		orders.add(order2);
	}
	
	public void displayOrders(){
		for (Order order : orders) {
	          System.out.println(order.getOrderID());
		}
		Scanner in=new Scanner(System.in);
		System.out.println("Please enter orderID: ");
		orderID=Integer.parseInt(in.nextLine());
		orderToShip=orders.get(orderID-1);
	}
	
	public void getInfoFromDatabase(){
		locationID=location.getLocationID();
		//select orders from db where in location locationID
		//for each order get products
		//pass info to createOrderList()
		createOrderList();
	}
	
	public void updateDatabase(Shipment shipment){
		
		System.out.println("Shipped Order: "+ shipment.getOrderID());
	}
}
