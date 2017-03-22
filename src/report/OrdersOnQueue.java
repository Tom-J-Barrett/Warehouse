package report;
import java.util.List;

import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;
import report.OrdersOnQueueBuilder;

public class OrdersOnQueue extends Report {
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
	
	public OrdersOnQueue(OrdersOnQueueBuilder builder){
		this.reportName="Orders On Queue Report";
		System.out.println("Orders to be processed");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
