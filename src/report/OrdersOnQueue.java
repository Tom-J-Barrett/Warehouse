package report;
import java.util.List;

import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;

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
	//private Employee employee;
	private Product product;
	private Item item;
	private String reportName;
	
	public OrdersOnQueue(ReportBuilder b){
		this.reportName=b.reportName;
		this.orders=b.orders;
		/*this.products=b.products;
		this.items=b.items;
		this.inventory=b.inventory;*/
		this.location=b.location;
		this.locationID=location.getLocationID();
		this.locationType=location.getLocationType();
		//this.employee=b.employee;	
	}
	
	@Override
	public String generateReport(){
		return "";
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(Order order: orders){
			product=order.getProducts().get(0);
			item=product.getItems().get(0);
			System.out.println("Order : "+ order.getOrderID());
			System.out.println("	Product ID: "+ product.getProductID());
			System.out.println("	Product Name: "+ product.getProductName());
			System.out.println("		Item ID: "+ item.getItemID());
			System.out.println("		Item ID: "+ item.getItemName());
		}
	}
}